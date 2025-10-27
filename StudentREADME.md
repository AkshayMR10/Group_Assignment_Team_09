# Student Role — README  
**Name:** Amrin Bushra Taj  
**NUID:** 002565908  

This README explains how to **run, navigate, and use** the *Student* workflow in the **Digital University** project.  
It focuses on student-facing screens, data flow, and application behavior specific to the Student role.

---

## 1) Overview  

Students can:

- **Manage Coursework** — view enrolled courses, credit load, and academic progress.  
- **Course Registration** — browse available courses by semester, search (ID / Name / Instructor), enroll or drop.  
- **Graduation Audit** — verify completed credits and check degree eligibility.  
- **Transcript Review** — view term-wise grades, GPA, and academic standing.  
- **Financial Management** — view tuition fees, payments, and refunds.  
- **My Profile** — update personal details and password.  

The student area lives inside a parent panel (`StudentWorkAreaJPanel`) which contains an **internal** `cardPanel` (CardLayout) used to switch between the 6 sub-panels.

---

## 2) Project Structure (Student area)

UserInterface.WorkAreas.StudentRole/
│
├─ StudentWorkAreaJPanel.java # parent container with 6 buttons & right-side cardPanel
│
├─ CourseworkJPanel.java # displays enrolled courses and total credits
├─ CourseRegistrationJPanel.java # search, enroll, and drop courses
├─ GraduationAuditJPanel.java # checks degree progress and eligibility
├─ TranscriptJPanel.java # shows GPA and academic standing
├─ FinancialManagementJPanel.java # tuition payment, refund info
└─ MyProfileJPanel.java # view and update student information

---

## 3) Prerequisites  

- **Java:** JDK 8+ (tested with Java 8)  
- **IDE:** NetBeans (recommended, uses GUI Builder)  
- **UI Toolkit:** Swing  
- **System Memory:** Minimum 2 GB  

---

## 4) Build & Run  

1. Open the project in **NetBeans**.  
2. **Clean and Build** the project.  
3. Run the launcher class (`ProfileWorkAreaMainFrame.java`).  
4. Login as a **Student** user from the seeded data.  

Example:  
Username: student1
Password: ****

---

## 5) Routing by Role (Main Frame)  

> Only the **main frame** manipulates the global `CardSequencePanel`.  
> The Student area uses its **own internal** `cardPanel` for sub-navigation.

```java
if (profile instanceof StudentProfile) {
    StudentWorkAreaJPanel studentPanel = new StudentWorkAreaJPanel(business, useraccount);
    CardSequencePanel.removeAll();
    CardSequencePanel.add("student", studentPanel);
    ((CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}
6) StudentWorkAreaJPanel (Container)

Buttons: Coursework, Course Registration, Graduation Audit, Transcript, Financials, My Profile.

Right side: cardPanel using CardLayout.

Each button shows the corresponding panel using:

cardLayout.show(cardPanel, CARD_COURSEWORK / CARD_REGISTRATION / CARD_AUDIT / CARD_TRANSCRIPT / CARD_FINANCIAL / CARD_PROFILE);


Do not call CardSequencePanel from inside student sub-panels.
Keep navigation limited to cardPanel only.
7) Data Model Hooks

Business → getCourseSchedule(semester) → returns List<CourseOffer>

CourseOffer

getCourse() → Course (ID / Title)

getFacultyProfile() → assigned instructor

getSeatlist() → list of enrolled students

Seat → isOccupied(), getSeatAssignment()

SeatAssignment

getCourseload() → getStudentProfile()

getGrade() / setGrade() (0.0–4.0 GPA)

StudentProfile → getPerson() (name/email/universityId)

CourseLoad → maintains current semester courses and credits

8) Panel Behaviors
A) CourseworkJPanel

Displays currently enrolled courses with:

Course ID, Name, Instructor, Credits

Calculates total enrolled credits.

Prevents overload beyond 8 credits.

if (student.getTotalCredits() + course.getCreditHours() > 8) {
    JOptionPane.showMessageDialog(null, "Credit limit exceeded!");
}

B) CourseRegistrationJPanel

Search Methods (3 types):

By Course ID (exact match)

By Course Name (partial match)

By Instructor Name (partial match)

Results populate in the same JTable.

Students can enroll or drop selected courses.

public Course searchById(String id) { ... }
public List<Course> searchByName(String name) { ... }
public List<Course> searchByInstructor(String instructor) { ... }

C) GraduationAuditJPanel

Tracks completed and in-progress credits.

Checks core course completion (e.g., INFO 5100).

Displays:

Status	Condition
✅ Eligible for Graduation	credits ≥ 32 and all core courses complete
❌ Not Ready Yet	otherwise
if (creditsCompleted >= 32 && hasCompletedCoreCourse("INFO 5100")) {
    lblStatus.setText("Eligible for Graduation ✅");
}

D) TranscriptJPanel

View term-wise GPA, overall GPA, and academic standing.

Calculates standing:

Good Standing: Term ≥ 3.0 & Overall ≥ 3.0

Warning: Term < 3.0

Probation: Overall < 3.0

Grade Mapping:

Grade	Points
A	4.0
A−	3.7
B+	3.3
B	3.0
C	2.0
F	0.0
E) FinancialManagementJPanel

Displays tuition balance, payment history, and refund eligibility.

Future integration: payment gateway simulation.

F) MyProfileJPanel

Loads current student details.

Allows editing email, phone, and password (name and ID are read-only).

Validates input before saving back to model.

9) Common Pitfalls & Fixes

Duplicate results in Course Search
→ Ensure conditionals in search distinguish by selected search type (ID/Name/Instructor).

Credit Overload Error
→ Verify credit calculation before enrollment confirmation.

Navigation not updating
→ Ensure all sub-panels are added to cardPanel before calling cardLayout.show().

10) Quick Test Script

Login as a Student.

Course Registration → search by Course ID → Enroll → verify in Coursework.

Graduation Audit → verify eligibility changes after completing courses.

Transcript → check GPA and standing.

Financial Management → confirm tuition details appear.

My Profile → update email → save → re-open to confirm update.

