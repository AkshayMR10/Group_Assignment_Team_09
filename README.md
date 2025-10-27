# Digital University — Team 09
**Institution:** Northeastern University • **Course:** INFO 5100 • **Semester:** Fall 2025  
**Project:** Access‑Controlled Digital University System (Role‑Based Administration)

**Team & Roles**
- **Akshay Madavalappil Ramesh (NUID: 002560270)** — Administrator
- **Venkata Sriya Kamarsu (NUID: 002560433)** — Faculty
- **Amrin Bushra Taj (NUID: 002565908)** — Student
- **Anushika Balamurugan (NUID: )** — Registrar

This document is the **single source of truth** for how to set up, run, and evaluate Team 09’s Digital University system. It consolidates the Administrator, Faculty, Student, and Registrar roles into a coherent, professional README suitable for submission.

---

## 1) Overview
The Digital University system demonstrates **role‑based access control** across four personas—Admin, Faculty, Student, and Registrar—implemented with Swing UI and a layered Business model. Each role has a dedicated Work Area with its own **CardLayout** navigation; the **main frame** handles login and global routing.

**Highlights**
- **Authentication & Authorization:** Secure login, role routing, profile‑backed accounts.
- **Data Management:** Pre‑seeded people, courses, semesters, enrollments; robust CRUD UIs.
- **Academics & Grading:** Faculty roster, per‑assignment gradebook, final GPA posting.
- **Analytics & Finance:** University‑wide dashboards and tuition rollups.
- **Quality:** Consistent validation, null‑safety, and clear separation of model vs UI.

---

## 2) Technology & Requirements
- **Language:** Java (JDK **8+**; Java 8 tested)
- **IDE:** NetBeans (GUI Builder used)
- **UI:** Swing (JPanel, JTable, CardLayout)
- **Build:** NetBeans Ant (Clean & Build)
- **Memory:** 2 GB minimum

> Java 8 is enforced in code style (no `var` in loops; explicit generics).

---

## 3) Setup & Run
1. **Open in NetBeans** → *Run → Clean and Build Project* → verify *BUILD SUCCESSFUL*.
2. **Run** `ProfileWorkAreaMainFrame.java`.
3. **Login** with seeded accounts (see seed code). Example:  
   - **Admin:** `admin / ****`  
   - **Student:** `student1 / ****`

**Routing**
- The main frame switches the global `CardSequencePanel` to the correct Work Area:
  - `AdminWorkAreaJPanel`, `FacultyWorkAreaJPanel`, `StudentWorkAreaJPanel`, `RegistrarWorkAreaJPanel`
- Inside each Work Area, an internal `cardPanel` (CardLayout) handles sub‑navigation.  
  **Do not** reference the global `CardSequencePanel` inside sub‑panels.

---

## 4) Seed Data (for Demo Readiness)
- **People/Profiles:** ≥30 persons (≥12 Students, ≥11 Faculty, ≥4 Admin, ≥2 Registrar)
- **Courses & Semesters:** 7 courses across **Fall 2025** and **Spring 2025**
- **Assignments:** Faculty assigned to CourseOffers
- **Enrollment:** 19 occupied seats overall
- **Finance:** Tuition calculated from credit‑hour rules
- **Grades:** Final GPA stored on `SeatAssignment` (0.0–4.0) where applicable

> Ensure `CourseLoad.getStudentProfile()` exists; it’s required to resolve roster rows from seat assignments.

---

## 5) Role Guides

### 5.1 Administrator (Akshay) — **Use Case Owner**
**Responsibilities**
- User Account Management (create, modify, delete; password reset & change)
- Person Registration with **auto‑generated University ID** and duplicate prevention
- Student/Faculty/Registrar Records with **3 search methods** each (Name / ID / Department)
- Faculty‑Course Assignment management (assign, reassign, unassign)
- **Analytics Dashboard** — 4 required metrics
- Admin profile management with functional password change

**Admin Features (by screen)**
1) **Administer User Accounts**  
   View, reset password, delete accounts; immediate table refresh.
2) **Register Persons (HR)**  
   Multi‑role registration, validation (email/phone), auto‑ID (`U00001`…), duplicate checks.
3) **Manage Students / Faculty / Registrar**  
   CRUD + **3 searches** (Name partial, ID exact, Department partial). Faculty view shows assigned courses.
4) **Faculty Course Assignments**  
   Semester filter; assign/reassign/remove instructor for CourseOffers.
5) **Analytics Dashboard (4 Required Metrics)**  
   - **Active Users by Role**  
   - **Courses Offered per Semester**  
   - **Enrollment per Course (by Semester)**  
   - **Tuition Revenue Summary** (e.g., 19 × $6,000 = **$114,000**)
6) **Admin Profile**  
   View/edit and **Change Password** (verifies current, validates new, updates account).

**Implementation Notes**
- Central validation via `InputValidator` (email/phone/password).
- University ID generation in `PersonDirectory.generateUniversityId()`.
- Password updates through `UserAccount.setPassword(newValue)`.

---

### 5.2 Faculty (Sriya)
**Capabilities**
- **Manage Courses:** See assigned courses per semester; capacity vs enrolled.
- **Manage Student Profiles (Roster):** Roster from occupied seats; open per‑student **Gradebook**.
- **Gradebook:** Assignment rows → Recalculate total → **Post Final** (percent→GPA onto `SeatAssignment`).
- **Performance Reports:** Grade distribution, average, enrollment; **export CSV**.
- **Tuition Insight:** Revenue collected for own courses (per semester).
- **My Profile:** Update email/phone/office/hours; name is read‑only.

**Key Data Flow**
- `CourseOffer` → `getSeatlist()` → `SeatAssignment.getCourseload().getStudentProfile()`  
- GPA stored on `SeatAssignment` (`getGrade()`/`setGrade()`), mapped to percent/letter for reports.

---

### 5.3 Student (Amrin)
**Capabilities**
- **Coursework:** Enrolled courses and total credits (prevents >8 credits/semester).
- **Course Registration:** Search (ID exact / Name partial / Instructor partial); enroll / drop.
- **Graduation Audit:** Tracks completed credits and core requirements (e.g., **INFO 5100**); flags eligibility (≥32 credits + core complete).
- **Transcript:** Term and cumulative GPA; academic standing (Good/Warning/Probation).
- **Financial Management:** Balance, payments, refund rules (gateway: future integration).
- **My Profile:** Edit email/phone/password (name & ID read‑only).

**Sample Logic**
```java
// Credit limit guard
if (student.getTotalCredits() + course.getCreditHours() > 8) {
    JOptionPane.showMessageDialog(this, "Credit limit exceeded!");
}
```

---

### 5.4 Registrar (Anushika)
**Capabilities**
- Create/manage **CourseOffers**, set capacity/room/time, assign faculty
- Admin‑side enrollment (enroll/drop on behalf of students)
- Department/semester reports; reconciliation of collected vs pending tuition

---

## 6) Architecture & Conventions
**UI**  
- Global routing in main frame; per‑role navigation via internal `cardPanel`.
- Tables use DefaultTableModel; dialogs for details/gradebook.

**Business Model (selected)**  
- `Business`, `Person/Directory`, `Profiles` (Student/Faculty/Registrar), `UserAccount/Directory`  
- `Course/Catalog`, `CourseSchedule`, `CourseOffer`, `Seat`, `SeatAssignment`, `CourseLoad`  
- `InputValidator` for reusable validation

**Conventions**  
- Prefer pass‑through helpers on `CourseOffer`:
  ```java
  public String getCourseId()    { return course.getCourseNumber(); }
  public String getCourseTitle() { return course.getName(); }
  ```
- No `var` in Java 8 code; use explicit generics:
  ```java
  for (Map.Entry<String,Integer> e : dist.entrySet()) { ... }
  ```
- Sub‑panels **must not** call the main `CardSequencePanel`.

---

## 7) Demo Script (Evaluator Ready)
1. **Admin**
   - Register person (auto‑ID; duplicate checks).
   - Manage Students/Faculty (3 searches) and update a record.
   - Assign faculty to a course; open Analytics Dashboard (verify 4 metrics).
2. **Registrar**
   - Create/modify a course offer; set capacity; assign faculty.
3. **Faculty**
   - Manage Courses → select semester → Refresh.
   - Manage Students → Load roster → open Gradebook → add rows → Recalculate → **Post Final**.
   - Performance Reports → Run; **Export CSV**.
   - Tuition Insight → Refresh (verify totals).
4. **Student**
   - Course Registration → search & enroll; verify in Coursework.
   - Graduation Audit → confirm eligibility updates.
   - Transcript → verify GPA & standing.

---

## 8) Troubleshooting
- **Empty Faculty roster:** Check faculty‑to‑offer assignment and **occupied** seats; ensure `CourseLoad.getStudentProfile()` exists & is set in seeding; match semester.
- **NPE on navigation:** Remove any lingering references to global `CardSequencePanel` inside sub‑panels; use `cardPanel` only.
- **Method missing (`getNumber`) on `Course`:** Use `getCourseNumber()`; consider `CourseOffer.getCourseId()` helper.
- **Java 8 compile errors:** Replace `var` with explicit types; ensure proper imports (e.g., `java.util.Map`).

---

## 9) Grading Alignment (Evidence Map)
- **Access Control & Security:** Enforced per role; screens restricted appropriately.
- **Pre‑populated Data:** Meets or exceeds minimums (persons, faculty, students, offers, enrollments).
- **UI/UX:** Clear labels, working navigation; consistent table refresh.
- **Code Quality:** Separation of concerns; validation utilities; helpers for model access.
- **Documentation:** This README + role‑specific guidance.
- **Validation & Null‑Safety:** Present across forms and navigation.

---

## 10) Known Limitations & Future Work
- In‑memory data only (no persistent DB).
- Plain‑text passwords (educational scope); future: hashing + policy.
- Single‑user session; no concurrency controls.
- Future: visual charts, export to PDF/Excel, audit logs, DB integration, payment gateway simulation.

---

## 11) Credits
Team 09 — **Akshay (Admin), Sriya (Faculty), Amrin (Student), Anushika (Registrar)**  
Thanks to the course staff for seed frameworks and review guidelines.
