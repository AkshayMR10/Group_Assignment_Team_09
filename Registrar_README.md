# Registrar Role — README
**Name:** Anushika Balamurgan  
**NUID:** 002047627  

This README explains how to run, navigate, and use the **Registrar workflow** in the *Digital University* project.  
It focuses on registrar-facing screens, data flow, and application behavior specific to the Registrar role.

---

## 1) Overview  
Registrars can:

- **Manage Student Enrollment** — view, enroll, and drop students from course offers.  
- **Manage Course Catalogs** — create, update, and retire course entries.  
- **Manage Course Schedules** — publish course schedules for semesters and set seat capacities.  
- **Review Academic Records** — monitor students’ grades, credits, and progress.  
- **Generate Reports** — produce semester-wise enrollment, performance, and course utilization summaries.  
- **Registrar Profile** — view and edit their office information, working hours, and assigned semester.  

The registrar area resides within the parent panel **RegistrarWorkAreaJPanel**, which contains an internal **cardPanel (CardLayout)** used to switch among these functional sub-panels.

---

## 2) Project Structure (Registrar area)
```
UserInterface.WorkAreas.RegistrarRole/
│
├─ RegistrarWorkAreaJPanel.java        # Parent container with left-side navigation buttons & right-side cardPanel
│
├─ ManageStudentEnrollmentJPanel.java  # Enroll/drop students into course offers
├─ ManageCourseCatalogJPanel.java      # Create/update/remove course catalog entries
├─ ManageCourseScheduleJPanel.java     # Define semester schedules and seat capacities
├─ ReviewAcademicRecordsJPanel.java    # Access and verify student academic data
├─ GenerateReportsJPanel.java          # Generate reports on enrollment, GPA, and credit distribution
└─ RegistrarProfileJPanel.java         # View and edit registrar profile info
```

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
3. Run the **ProfileWorkAreaMainFrame.java** launcher class.  
4. Login using the **Registrar** credentials from the seeded data.  

Example:  
```
Username: registrar1  
Password: ****  
```

---

## 5) Routing by Role (Main Frame)  
The **Main Frame** controls the global `CardSequencePanel`.  
Each role (Admin, Faculty, Student, Registrar) is routed to its respective work area.  
The **RegistrarWorkAreaJPanel** uses its own **internal cardPanel** for navigation within registrar-related screens.  

---

## 6) RegistrarWorkAreaJPanel (Container)  
**Buttons:**
- Manage Enrollment  
- Course Catalog  
- Course Schedule  
- Academic Records  
- Reports  
- My Profile  

**Right side:** `cardPanel` using `CardLayout`.  

Each button switches the view using:  
```java
cardLayout.show(cardPanel, CARD_ENROLLMENT / CARD_COURSECATALOG / CARD_SCHEDULE / CARD_RECORDS / CARD_REPORTS / CARD_PROFILE);
```

⚠️ **Note:**  
Do not access the global `CardSequencePanel` inside registrar sub-panels.  
Keep navigation limited to the internal `cardPanel`.

---

## 7) Data Model Hooks  
- `Business → getCourseCatalog()` → returns list of **Course** objects  
- `Business → getCourseSchedule(semester)` → returns **CourseSchedule**  
- `CourseSchedule → getCourseOfferList()` → list of course offers  
- `CourseOffer → getSeatlist()` → seat allocation & enrollment management  
- `Seat → getSeatAssignment()` → linked student enrollment  
- `StudentProfile → getCourseLoadBySemester()` → returns enrolled courses and grades  
- `RegistrarProfile → getAssignedSemester()` → controls registrar’s operational term  

---

## 8) Panel Behaviors  

### A) ManageStudentEnrollmentJPanel  
- Displays students and available course offers.  
- Allows enrolling or dropping students.  
- Prevents enrollment if:
  - No vacant seat is available.  
  - Student credit limit exceeds allowed load.  
- Updates both the student’s `CourseLoad` and the course’s `SeatAssignment`.  
```java
if (!seat.isOccupied()) {
    seat.assignSeat(studentProfile);
} else {
    JOptionPane.showMessageDialog(null, "No empty seat available!");
}
```

---

### B) ManageCourseCatalogJPanel  
- Create or edit course entries (ID, Title, Credits, Description).  
- Prevents duplicate Course IDs.  
- Allows deactivation (retirement) of outdated courses.  

---

### C) ManageCourseScheduleJPanel  
- Create semester schedules (Fall/Spring).  
- Add course offers from catalog.  
- Set seat capacities and assign faculty.  
- Automatically initializes `Seat` objects for each course offer.  

---

### D) ReviewAcademicRecordsJPanel  
- View all student profiles, their enrolled courses, and grades.  
- Supports filtering by semester or student name.  
- Displays GPA and total completed credits.  
- Enables registrar verification for graduation eligibility.  

---

### E) GenerateReportsJPanel  
- Produces structured reports such as:
  - **Enrollment Report:** Number of students per course.
  - **Performance Report:** Average GPA by semester.
  - **Course Utilization:** Seat occupancy rate.  
- Uses tabular view and export-to-CSV option (future integration).  

---

### F) RegistrarProfileJPanel  
- Displays registrar name, department, office location, and hours.  
- Allows updating contact info and assigned semester.  
- All changes update the underlying `RegistrarProfile` object.  

---

## 9) Common Pitfalls & Fixes  
| Issue | Cause | Fix |
|-------|--------|-----|
| “No Empty Seat Available” even when seats exist | Mismatch in case sensitivity for course IDs in `getSeatList()` | Convert IDs to lowercase before comparison |
| Student not appearing in enrollment table | `SeatAssignment` not linked to correct `CourseLoad` | Verify both course and semester references |
| Schedule not updating | Sub-panel not revalidated after new data | Call `refreshTable()` or `populateScheduleTable()` |
| Duplicate course offers | Multiple schedules for same course/semester | Check for existing course offer before creating new |

---

## 10) Quick Test Script  
1. **Login as Registrar.**  
2. **Manage Course Catalog** → Create new course “INFO 6200”.  
3. **Manage Course Schedule** → Add “INFO 6200” for Fall 2025 → Set 100 seats.  
4. **Manage Student Enrollment** → Enroll a student into “INFO 6200”.  
5. **Review Academic Records** → Verify the student’s record update.  
6. **Generate Reports** → View Enrollment summary for Fall 2025.  
7. **My Profile** → Update office hours → Save → Confirm refresh.
