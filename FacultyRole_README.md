# Faculty Role — README

This README explains how to **run, navigate, and use** the *Faculty* workflow in the **Digital University** project. It focuses only on the faculty-facing screens and the data hooks they rely on.

---

## 1) Overview

Faculty can:

- **Manage Courses** — view courses they teach by semester, enrollment counts, capacity, and (optionally) upload syllabus.
- **Manage Student Profiles (Roster)** — load enrolled students for a selected course, open a **Gradebook** dialog, view transcript/rank (optional).
- **My Profile** — view/update email, phone, office, office hours (name is read-only).
- **Performance Reports** — grade distribution, average, enrollment; export to CSV.
- **Tuition Insight** — revenue per course and total collected for a semester.

The faculty area lives inside a parent panel (`FacultyWorkAreaJPanel`) which contains an **internal** `cardPanel` (CardLayout) used to switch between the 5 sub-panels.

---

## 2) Project Structure (Faculty area)

```
UserInterface.WorkAreas.FacultyRole/
│
├─ FacultyWorkAreaJPanel.java        # parent container with 5 buttons & right-side cardPanel
│
├─ ManageCoursesJPanel.java          # courses taught by faculty, per semester
├─ ManageStudentProfilesJPanel.java  # roster (load students, open Gradebook)
├─ MyProfileJPanel.java              # edit email/phone/office/office hours
├─ PerformanceReportsJPanel.java     # grade distribution/average/enrollment, CSV export
├─ TuitionInsightJPanel.java         # paid tuition per course + total
│
├─ GradebookDialog.java              # modal wrapper for gradebook
└─ GradebookPanel.java               # assignment rows, recalc, post final
```

---

## 3) Prerequisites

- **Java:** JDK 8+ (code is Java 8 compatible — no use of `var`)
- **IDE:** NetBeans (GUI Builder used)
- **UI Toolkit:** Swing

---

## 4) Build & Run

1. Open the project in **NetBeans** (JDK 8+).
2. **Clean and Build**.
3. **Run** the launcher class (main frame).
4. Login as a **Faculty** user from the seeded data.

---

## 5) Routing by Role (Main Frame)

> Only the **main frame** should manipulate the app-level `CardSequencePanel`.  
> The faculty area itself uses its own internal `cardPanel` for sub-navigation.

```java
// Pseudocode in your main frame after successful login:
UserAccount useraccount = authenticatedUserAccount;
PersonProfile profile = useraccount.getAssociatedPersonProfile();

if (profile instanceof FacultyProfile) {
    FacultyWorkAreaJPanel faculty = new FacultyWorkAreaJPanel(business, useraccount);
    CardSequencePanel.removeAll();
    CardSequencePanel.add("faculty", faculty);
    ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}
```

---

## 6) FacultyWorkAreaJPanel (container)

- Has buttons: **Manage Courses**, **Manage Students Profiles**, **My Profile**, **Performance Reports**, **Tuition Insight**.
- Right side is a `JPanel` named **`cardPanel`** with **CardLayout**.
- Button handlers call:

```java
cardLayout.show(cardPanel, CARD_COURSES / CARD_STUDENTS / CARD_PROFILE / CARD_REPORTS / CARD_TUITION);
```

> **Do not** call `CardSequencePanel` from inside faculty panels.

---

## 7) Data Model Hooks (read-only summary)

- `Business` → `getCourseSchedule(semester)` → `CourseSchedule.getSchedule()` → `List<CourseOffer>`
- `CourseOffer`
  - `getCourse()` → `Course` (ID/title via `getCourseNumber()`, `getName()`)
  - `getFacultyProfile()` → assigned instructor
  - `getSeatlist()` → `List<Seat>`; `getEnrolledCount()`; `getTotalCourseRevenues()`
- `Seat` → `isOccupied()`, `getSeatAssignment()`
- `SeatAssignment` → `getCourseload()` → `getStudentProfile()`, `getGrade()`/`setGrade()` (GPA 0.0–4.0)
- `CourseLoad` → **must have** `getStudentProfile()` (and setter) so the roster can resolve the student
- `UserAccount` → `getAssociatedPersonProfile()` (cast to `FacultyProfile`)
- `FacultyProfile` → `getPerson()`; `office`, `officeHours`
- `StudentProfile` → `getPerson()` (name/email/universityId)

> If your `CourseOffer` field is named `course`, add helpers:
> ```java
> public String getCourseId()    { return course.getCourseNumber(); }
> public String getCourseTitle() { return course.getName(); }
> ```

---

## 8) Panel Behaviors

### A) ManageCoursesJPanel
- **UI:** `cmbSemester`, `btnRefresh`, `tblCourses` (columns: Course ID, Title, Section, Capacity, Enrolled), optional actions at bottom.
- **Refresh logic:**
  1. `CourseSchedule cs = business.getCourseSchedule(selectedSemester);`
  2. Iterate `cs.getSchedule()` (List<CourseOffer>).
  3. Keep those with `co.getFacultyProfile() == myFaculty`.
  4. Row: `co.getCourse().getCourseNumber()`, `co.getCourse().getName()`, `capacity = co.getSeatlist().size()`, `enrolled = co.getEnrolledCount()`.

### B) ManageStudentProfilesJPanel
- **UI:** `cmbCourse`, `btnLoad`, `tblRoster` (StudentId/Name/Email/Progress%/Letter), `btnOpenGradebook`, `btnViewTranscript`, `btnRankAndGPA`.
- **On load:**
  - Build `myOffers` with **all** `CourseOffer`s where `getFacultyProfile()==myFaculty` (across semesters) and populate `cmbCourse`.
  - For selected offer, iterate `offer.getSeatlist()`, keep `seat.isOccupied()`, then:
    ```java
    SeatAssignment sa = seat.getSeatAssignment();
    StudentProfile sp = sa.getCourseload().getStudentProfile(); // requires CourseLoad getter
    float gpa = sa.getGrade(); // 0.0–4.0
    double percent = gpa / 4.0 * 100.0;
    String letter = ...  // A/A-/B+ mapping
    ```
  - Add rows to `tblRoster`.

- **Open Gradebook:**
  - Pass `Business`, the selected `CourseOffer`, and the selected `StudentProfile` to `GradebookDialog`.
  - After dialog closes, you may recompute the selected row’s Progress/Letter.

### C) GradebookDialog / GradebookPanel
- Shows **assignment** rows. If no rows exist, seeds a few (Homework/Midterm/Project/Final).
- If `SeatAssignment.getGrade()` already has a GPA, also show a **“Final (from transcript)”** row.
- **Recalculate** computes total percent & letter; **Post Final** converts to GPA and writes `sa.setGrade(gpa)`.

### D) MyProfileJPanel
- Loads from `FacultyProfile` and `Person`.
- `txtName` is **read-only** (`setEditable(false)`).
- **Save** validates then writes email/phone/office/office hours back to the model.

### E) PerformanceReportsJPanel
- **Filters:** semester + course.
- **Run:**
  - Locate selected `CourseOffer`.
  - Build a grade distribution by walking occupied seats and reading `sa.getGrade()` (GPA → letter).
  - Compute **Average** (GPA→percent) & **Enrollment**.
- **Export CSV** writes the distribution table to a file.

### F) TuitionInsightJPanel
- **Refresh** for selected semester:
  - For each `CourseOffer` with `getFacultyProfile()==myFaculty`: show CourseId, Title, Enrolled, `co.getTotalCourseRevenues()`.
  - Sum revenues → `lblTotalCollected`.

---

## 9) Common Pitfalls & Fixes

- **NPE on `CardSequencePanel` inside faculty panels**  
  Remove **all** references to `CardSequencePanel` from faculty sub-panels. Only the main frame should use it. Inside faculty, use `cardPanel` + `CardLayout`.

- **“cannot find symbol: getNumber()” on Course**  
  Your `Course` has `getCourseNumber()`. Use `co.getCourse().getCourseNumber()` (or create `co.getCourseId()` helper).

- **Roster empty**  
  - Ensure `myOffers` is filled for **all** semesters where `co.getFacultyProfile()==myFaculty`.  
  - Ensure `CourseLoad.getStudentProfile()` exists and is set when creating course loads.  
  - Ensure seats are **occupied** (enrollments exist) in the seed.

- **Java 8 error with `var`**  
  Replace `for (var e : map.entrySet())` with  
  `for (Map.Entry<String,Integer> e : map.entrySet())` and add `import java.util.Map;`.

---

## 10) Quick Test Script

1. **Login** as a Faculty user.  
2. **Manage Courses** → pick semester → **Refresh** → confirm your courses appear.  
3. **Manage Students** → pick a course → **Load** → confirm roster shows.  
   - Select a student → **Open Gradebook** → add scores → **Recalculate** → **Post Final**.  
4. **Performance Reports** → same course/semester → **Run** → confirm distribution/average/enrollment → **Export CSV** (optional).  
5. **Tuition Insight** → pick semester → **Refresh** → confirm totals.  
6. **My Profile** → change office hours → **Save** → switch panel and come back to confirm.

---


