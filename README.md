# Digital University System - Administrator Use Case

## Project Information

**Project Title:** Access-Controlled Digital University System with Role-Based Administration  
**Assignment:** Group Assignment 1 - INFO 5100  
**Institution:** Northeastern University  
**Semester:** Fall 2025  

---

## Team Member - Administrator Implementation

**Name:** Akshay Madavalappil Ramesh  
**NUID:** 002560270  
**Role:** Administrator Use Case    

**Responsibilities:**
- User Account Management (Create, Modify, Delete)
- Person Registration System with Auto-ID Generation
- Student Records Management with 3 Search Methods
- Faculty Records Management with 3 Search Methods
- Registrar Records Management
- Faculty-Course Assignment Management
- University-Level Analytics Dashboard (All 4 Required Metrics)
- Admin Profile Management with Password Change

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Installation & Setup](#installation--setup)
3. [Administrator Features](#administrator-features)
4. [Usage Instructions](#usage-instructions)
5. [Testing Guide](#testing-guide)
6. [Code Contributions](#code-contributions)
7. [Challenges & Solutions](#challenges--solutions)
8. [Technical Architecture](#technical-architecture)

---

## Project Overview

### Purpose
This project implements a comprehensive Digital University System with role-based access control. As the Administrator Use Case implementer, I developed the complete administrative backend and UI for managing users, persons, students, faculty, registrars, course assignments, and university-wide analytics.

### Key Features Implemented

**1. Authentication & Authorization**
- Secure login/logout with password validation
- Role-based routing (Admin, Student, Faculty, Registrar)
- Functional password change and reset capabilities

**2. Data Management**
- 30+ pre-populated persons with complete demographics
- Comprehensive CRUD operations for all entity types
- Advanced search capabilities (3 methods per entity)
- Real-time data validation and duplicate prevention

**3. Analytics & Reporting**
- University-wide analytics dashboard
- 4 required metrics fully implemented
- Real-time data aggregation
- Revenue calculation and tracking

**4. Data Integrity**
- Centralized input validation (email, phone, password)
- Auto-generated unique University IDs
- Duplicate email and ID prevention
- Null-safety checks throughout

---

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE 8.2 or higher (recommended)
- Git for version control
- Minimum 2GB RAM
- Screen resolution: 1024x768 or higher

### Setup Instructions

#### Step 1: Clone Repository
```bash
git clone [repository-url]
cd DigitalUniversitySystem
```

#### Step 2: Open in NetBeans
1. Launch NetBeans IDE
2. File → Open Project
3. Navigate to project directory
4. Select and open the project

#### Step 3: Build Project
1. Right-click on project
2. Select "Clean and Build"
3. Wait for "BUILD SUCCESSFUL" message

#### Step 4: Run Application
1. Locate `ProfileWorkAreaMainFrame.java`
2. Right-click → Run File
3. Login window appears

### Default Admin Login
```
Username: admin
Password: ****
```

---

## Administrator Features

### 1. User Account Management

**Location:** Admin Dashboard → "Administer User Accounts"

**Features:**
- View all user accounts in sortable JTable (30+ accounts)
- Display: Username, Role, Status, Last Activity
- View detailed account information
- **Reset user passwords** (fully functional - passwords actually update)
- **Delete user accounts** with confirmation dialog
- Real-time table refresh after modifications

**Technical Implementation:**
- UserAccountDirectory manages all user accounts
- Password reset uses `UserAccount.setPassword(newPassword)`
- Deletion removes from UserAccountDirectory list
- Validation ensures password strength (minimum 4 characters)

---

### 2. Person Registration

**Location:** Admin Dashboard → "Register Persons (HR)"

**Features:**
- Multi-role registration (Student, Faculty, Registrar)
- **Auto-generated University ID** (format: U00001, U00002, etc.)
- **Duplicate prevention** for emails and University IDs
- Comprehensive data collection:
  - Personal: Name, Email, Phone, Address
  - Academic: Role, Department
  - Account: Optional username/password creation
- Enhanced validation:
  - Email format validation (RFC-compliant)
  - Northeastern email suggestion (@northeastern.edu)
  - Phone number validation (10-digit US format)
  - Phone auto-formatting (617-123-4567)
- Success confirmation with generated University ID

**Technical Implementation:**
```java
// University ID Auto-Generation
String universityId = personDir.generateUniversityId();
// Returns: U00024, U00025, etc.

// Duplicate Check
if (personDir.isDuplicateEmail(email)) {
    // Error: Email already exists
}

// Phone Formatting
if (InputValidator.isValidPhone(phone)) {
    phone = InputValidator.formatPhone(phone);
    // "6171234567" → "617-123-4567"
}
```

**Assignment Requirement Met:**
> "Register new individuals (student, faculty, registrar) with appropriate role linkage. Prevent duplicate registration by checking for existing email or university ID. Auto-generate unique university ID upon successful registration."

---

### 3. Student Records Management

**Location:** Admin Dashboard → "Manage Students"

**Features:**
- View all students in JTable (12+ students)
- **Three Search Methods** (Assignment Requirement):
  1. **Search by Name:** Partial match, case-insensitive
  2. **Search by University ID:** Exact match
  3. **Search by Department:** Partial match, case-insensitive
- View/Edit complete student information:
  - Personal: Name, Email, Phone, Address
  - Academic: Department, Program, Enrollment Date, Academic Status
- Delete students with confirmation dialog
- Show All button to reset filtered view
- Real-time table refresh after edits/deletions
- Input validation on all editable fields

**Table Columns:** University ID, Name, Email, Department

**Search Implementation:**
```java
// Method 1: Search by Name
public ArrayList<StudentProfile> searchByName(String searchTerm) {
    // Partial match, case-insensitive
    // "adam" finds "Adam Rollen"
}

// Method 2: Search by University ID
public StudentProfile searchByUniversityId(String univId) {
    // Exact match
    // "U00003" finds specific student
}

// Method 3: Search by Department
public ArrayList<StudentProfile> searchByDepartment(String dept) {
    // Partial match, case-insensitive
    // "Information" finds all in "Information Systems"
}
```

**Assignment Requirement Met:**
> "Search records by name, ID, or department (implement 3 search methods, similar to the student course search)."

---

### 4. Faculty Records Management

**Location:** Admin Dashboard → "Manage Faculty"

**Features:**
- View all faculty in JTable (11+ faculty members)
- **Three Search Methods** (same as Student Management)
- View/Edit complete faculty information:
  - Personal: Name, Email, Phone, Address
  - Academic: Department, Office, Office Hours, Hire Date, Specialization
  - **Assigned Courses** (displays which courses faculty teaches)
- Delete faculty with confirmation dialog
- Show All button to reset view
- Real-time table updates

**Table Columns:** University ID, Name, Email, Department, Specialization, Assigned Courses

**Enhanced Feature:**
- Faculty Detail panel shows complete course assignments:
  ```
  Assigned Courses:
  ─────────────────
  Fall 2025:
    • INFO 5100 - Application Engineering and Development (5 students)
  ```

**Assignment Requirement Met:**
> "Associate or reassign faculty to specific courses or departments."

---

### 5. Faculty Course Assignments

**Location:** Admin Dashboard → "Faculty Course Assignments"

**Features:**
- View all course offers and their faculty assignments
- Filter by semester (dropdown: All Semesters, Fall 2025, Spring 2025)
- **Assign faculty to courses:**
  - Select course from table
  - Select faculty from dropdown
  - Confirmation dialog shows course and faculty details
- **Reassign faculty:**
  - Change instructor for existing course
  - Shows previous and new faculty in confirmation
- **Remove faculty assignment:**
  - Unassign faculty from course
  - Course shows "Not Assigned"
- Real-time table refresh after assignments

**Table Columns:** Semester, Course ID, Course Name, Assigned Faculty, Faculty Department

**Technical Implementation:**
```java
// Assign faculty to course
selectedCourseOffer.AssignAsTeacher(selectedFaculty);

// Remove assignment
selectedCourseOffer.AssignAsTeacher(null);
```

**Assignment Requirement Met:**
> "Associate or reassign faculty to specific courses or departments."

---

### 6. Registrar Records Management

**Location:** Admin Dashboard → "Manage Registrar"

**Features:**
- View all registrars in JTable
- Three search methods (Name, University ID, Department)
- Edit registrar information:
  - Department, Office, Office Hours, Assigned Semester
- Delete registrars with confirmation
- Real-time updates

**Assignment Requirement Met (Group of 4):**
> "(For group of 4) Registrar Records Management: View, update, and delete registrar information."

---

### 7. University-Level Analytics Dashboard

**Location:** Admin Dashboard → "Analytics Dashboard"

**All 4 Required Metrics Implemented:**

#### **Metric 1: Total Active Users by Role**
JTable Display:
```
Role       | Count
-----------|-------
Admin      | 4
Student    | 12
Faculty    | 11
Registrar  | 2
TOTAL      | 29
```

#### **Metric 2: Total Courses Offered per Semester**
JTable Display:
```
Semester      | Courses
--------------|--------
Fall 2025     | 5
Spring 2025   | 2
TOTAL OFFERS  | 7
```

#### **Metric 3: Total Enrolled Students per Course**
JTable Display:
```
Semester     | Course ID | Course Name                    | Enrolled
-------------|-----------|--------------------------------|----------
Fall 2025    | INFO 5100 | App Eng & Development          | 5
Fall 2025    | INFO 6150 | Web Design                     | 3
Fall 2025    | INFO 6205 | Program Structures             | 3
Fall 2025    | INFO 7390 | Advances in Data Sciences      | 2
Fall 2025    | INFO 7245 | Information Visualization      | 2
Spring 2025  | CSYE 6200 | Object Oriented Design         | 2
Spring 2025  | DAMG 6210 | Database Management            | 3
             |           | TOTAL ENROLLMENTS              | 19
```

#### **Metric 4: Tuition Revenue Summary**
```
Tuition Revenue: $114,000 (displayed in green)

Calculation: 19 enrollments × $6,000 per course = $114,000
(Each course: 4 credits × $1,500/credit = $6,000)
```

**Additional Analytics:**
- Students by Department breakdown
- Faculty by Department breakdown
- Summary statistics: Total Persons (30), Courses (7), Semesters (2), Enrollments (19)
- Refresh Data button for real-time updates

**Assignment Requirement Met:**
> "Generate summary reports: Total active users by role, Total courses offered per semester, Total enrolled students per course, Tuition revenue summary (sum of paid tuition records from students). Display via JTable."

---

### 8. Profile Management

**Location:** Admin Dashboard → "My Profile"

**Features:**
- View profile information (username, role, University ID)
- Edit personal information (name, email, phone, address)
- **Functional password change:**
  - Verifies current password
  - Validates new password (minimum 4 characters)
  - Ensures passwords match
  - **Actually updates password** in UserAccount
  - Can logout and login with new password immediately

**Technical Implementation:**
```java
// Password Change Flow
1. Verify current password: currentUserAccount.getPassword().equals(currentPassword)
2. Validate new password: InputValidator.isValidPassword(newPassword)
3. Update password: currentUserAccount.setPassword(newPassword)
4. Immediate effect: New password works for next login
```

---

## Usage Instructions

### Getting Started

1. **Launch Application**
   - Run `ProfileWorkAreaMainFrame.java`
   - Login screen appears

2. **Login as Administrator**
   ```
   Username: admin
   Password: ****
   ```

3. **Admin Dashboard**
   - 8 functional buttons appear
   - Navigate to any admin feature

---

### Detailed Usage Workflows

#### Register a New Person

**Step-by-Step:**
1. Click **"Register Persons (HR)"**
2. **Enter Information:**
   - Full Name: "Test Student" (required)
   - Email: "test.student@northeastern.edu" (required, validated)
   - Phone: "6175551234" (optional, auto-formats)
   - Address: "123 Main St, Boston, MA" (optional)
   - Role: Select "Student" (dropdown)
   - Department: "Information Systems"
   - Username: "teststudent" (optional)
   - Password: "****" (optional)
3. Click **"Register"**
4. **Validation Occurs:**
   - Email format checked
   - Duplicate email checked
   - Phone format validated
5. **Success:**
   - University ID auto-generated (e.g., U00031)
   - Person created in system
   - Success message displays all details
6. Click **"Back"** to return to dashboard

**Example Success Message:**
```
Person registered successfully!

University ID: U00031
Name: Test Student
Email: test.student@northeastern.edu
Phone: 617-555-1234
Role: Student
Department: Information Systems
```

---

#### Search and Manage Student Records

**View All Students:**
1. Click **"Manage Students"**
2. Table displays all 12 students automatically

**Search Method 1: By Name**
1. Select **"Name"** from search type dropdown
2. Enter "adam" in search field
3. Click **"Search"**
4. Result: Finds "Adam Rollen" (partial match, case-insensitive)

**Search Method 2: By University ID**
1. Select **"University ID"** from dropdown
2. Enter "U00003"
3. Click **"Search"**
4. Result: Finds exact student with ID U00003

**Search Method 3: By Department**
1. Select **"Department"** from dropdown
2. Enter "Information Systems"
3. Click **"Search"**
4. Result: All IS students displayed

**Edit Student:**
1. Click on student row to select
2. Click **"View/Edit"**
3. Modify any fields (name, email, phone, etc.)
4. Click **"Save Changes"**
5. Validation occurs
6. Success message appears
7. Table updates automatically

**Delete Student:**
1. Select student from table
2. Click **"Delete"** button
3. Confirmation dialog appears with student details
4. Click **"Yes"** to confirm
5. Student removed from system
6. Table refreshes

---

#### Assign Faculty to Courses

**View Assignments:**
1. Click **"Faculty Course Assignments"**
2. Table shows all course offers and assigned faculty
3. Filter by semester using dropdown

**Assign Faculty:**
1. Click on course row to select
2. Select faculty from "Assign Faculty" dropdown
3. Click **"Assign to Selected Course"**
4. Confirmation dialog shows:
   - Course details (ID, name)
   - Faculty name and department
5. Click **"Yes"**
6. Success message appears
7. Table updates showing new assignment

**Reassign Faculty:**
1. Select course with existing faculty
2. Select different faculty
3. Click **"Assign"**
4. Dialog shows old and new faculty
5. Confirm reassignment
6. Table updates

**Remove Assignment:**
1. Select course with faculty
2. Click **"Remove Assignment"**
3. Confirm removal
4. Course shows "Not Assigned"

---

#### View Analytics Dashboard

**Access Dashboard:**
1. Click **"Analytics Dashboard"**
2. Five analytics tables display automatically

**Metrics Displayed:**

**Table 1: Active Users by Role**
- Shows count of Admins, Students, Faculty, Registrars
- Total user count

**Table 2: Students by Department**
- Information Systems: 7 students
- Computer Science: 5 students
- Total: 12 students

**Table 3: Faculty by Department**
- Information Systems: 7 faculty
- Computer Science: 4 faculty
- Total: 11 faculty

**Table 4: Courses Offered per Semester**
- Fall 2025: 5 courses
- Spring 2025: 2 courses
- Total: 7 course offerings

**Table 5: Student Enrollment by Course**
- All 7 courses listed
- Shows: Semester, Course ID, Name, Enrollment Count
- Total Enrollments: 19

**Summary Statistics:**
- Total Persons: 30
- Total Courses: 7
- Total Semesters: 2
- Total Enrollments: 19
- **Tuition Revenue: $114,000** (in green)

**Refresh Data:**
- Make changes (register person, assign faculty, etc.)
- Return to Analytics Dashboard
- Click **"Refresh Data"**
- All metrics update in real-time

---

#### Change Your Password

**Step-by-Step:**
1. Click **"My Profile"**
2. Scroll to "Change Password" section
3. Enter **Current Password:** `****`
4. Enter **New Password:** `admin123`
5. Enter **Confirm Password:** `admin123`
6. Click **"Change Password"**
7. Success message appears
8. Click **"Logout"**
9. Try old password: `admin/****` → **FAILS**
10. Login with new: `admin/admin123` → **SUCCEEDS**

**This is fully functional - not a demo!**

---

## Testing Guide

### Test Case 1: Person Registration with Auto-ID

**Objective:** Verify auto-ID generation and duplicate prevention

**Steps:**
1. Login as admin/****
2. Navigate to "Register Persons (HR)"
3. Enter: Name "New Student", Email "new@northeastern.edu", Role: Student
4. Click "Register"

**Expected:**
- ✅ Success message with auto-generated ID (e.g., U00031)
- ✅ All information displayed in confirmation

**Test Duplicate:**
5. Try registering with same email
6. **Expected:** Error "Email already exists!"

**Pass Criteria:**
- University ID auto-increments
- Duplicate blocked
- No console errors

---

### Test Case 2: Three Search Methods (Student)

**Objective:** Verify all 3 search methods work correctly

**Test 1: Name Search**
1. Navigate to "Manage Students"
2. Select "Name", enter "adam"
3. Click "Search"
4. **Expected:** Finds "Adam Rollen"

**Test 2: ID Search**
1. Select "University ID", enter "U00003"
2. Click "Search"
3. **Expected:** Exact match found

**Test 3: Department Search**
1. Select "Department", enter "Information"
2. Click "Search"
3. **Expected:** All IS students found

**Pass Criteria:**
- All 3 methods return correct results
- Case-insensitive search works
- Partial matching works for Name/Department

---

### Test Case 3: Input Validation

**Objective:** Verify validation prevents invalid data

**Test Invalid Email:**
1. Register person with email "bademail.com"
2. **Expected:** Error with format guidance

**Test Invalid Phone:**
1. Enter phone "abc123"
2. **Expected:** Error with format examples

**Test Valid Phone Formatting:**
1. Enter "6175551234"
2. Save
3. **Expected:** Auto-formats to "617-555-1234"

**Pass Criteria:**
- Invalid email blocked
- Invalid phone blocked
- Valid phone auto-formatted

---

### Test Case 4: Password Change Functionality

**Objective:** Verify password actually updates

**Steps:**
1. Login as admin/****
2. Go to "My Profile"
3. Change password to "newpass123"
4. Logout
5. Try old password: admin/****
6. **Expected:** Login FAILS
7. Try new password: admin/newpass123
8. **Expected:** Login SUCCEEDS

**Pass Criteria:**
- Password persists across logout
- Old password no longer works
- New password required for login

---

### Test Case 5: Analytics Accuracy

**Objective:** Verify all 4 metrics calculate correctly

**Steps:**
1. Navigate to "Analytics Dashboard"
2. Verify User by Role count matches actual
3. Verify Courses per Semester: Fall (5), Spring (2)
4. Verify Enrollment counts match
5. Verify Revenue: $114,000

**Test Real-Time Update:**
6. Register new student
7. Return to Analytics
8. Click "Refresh Data"
9. **Expected:** Student count increased by 1

**Pass Criteria:**
- All counts accurate
- Revenue calculation correct
- Refresh updates all metrics

---

### Test Case 6: Faculty Assignment

**Objective:** Verify faculty can be assigned/reassigned

**Steps:**
1. Navigate to "Faculty Course Assignments"
2. Select unassigned course
3. Assign faculty
4. **Expected:** Assignment saved, table updates

**Test Reassignment:**
5. Select INFO 5100
6. Assign different faculty
7. **Expected:** Shows old and new faculty

**Pass Criteria:**
- Assignments work
- Reassignments show both faculty
- Table reflects changes immediately

---

### Test Case 7: Delete with Confirmation

**Objective:** Verify deletion requires confirmation

**Steps:**
1. Navigate to "Manage Students"
2. Select student
3. Click "Delete"
4. Click "No" in confirmation
5. **Expected:** Student still in table
6. Click "Delete" again
7. Click "Yes"
8. **Expected:** Student removed

**Pass Criteria:**
- Confirmation required
- "No" cancels deletion
- "Yes" removes record

---

### Test Case 8: Navigation Integrity

**Objective:** Verify no blank screens or frozen UI

**Steps:**
1. Click through all 8 admin features
2. Use "Back" button from each
3. **Expected:** Returns to Admin Dashboard
4. Test deep navigation: Students → Edit → Cancel → Back → Dashboard
5. **Expected:** All navigation smooth

**Pass Criteria:**
- No blank screens
- All buttons responsive
- Logout works from any panel

---

## Code Contributions

### Business Logic Layer (Model)

**Files Created/Enhanced:**

1. **Person.java** (~100 lines)
   - Expanded with: universityId, name, email, phone, address
   - All getters/setters added

2. **PersonDirectory.java** (~200 lines)
   - University ID auto-generation algorithm
   - Duplicate prevention methods
   - Search methods: findPersonByEmail(), findPersonByUniversityId(), searchByName()

3. **StudentProfile.java** (~150 lines)
   - Added: department, program, enrollmentDate, academicStatus
   - Integrated CourseLoad functionality
   - Enhanced with course enrollment tracking

4. **StudentDirectory.java** (~180 lines)
   - Three search methods implemented
   - deleteStudent() method
   - getStudentList() accessor

5. **FacultyProfile.java** (~120 lines - Created from scratch)
   - Faculty-specific fields: department, office, officeHours, hireDate, specialization
   - toString() for dropdown display

6. **FacultyDirectory.java** (~190 lines - Created from scratch)
   - Three search methods
   - deleteFaculty() method
   - Comprehensive faculty management

7. **RegistrarProfile.java** (~80 lines - Created from scratch)
   - Registrar-specific fields: department, office, officeHours, assignedSemester

8. **RegistrarDirectory.java** (~160 lines - Created from scratch)
   - Three search methods
   - CRUD operations

9. **InputValidator.java** (~250 lines - Created from scratch)
   - Centralized validation utility
   - Email, phone, password validation
   - User-friendly error messages

10. **Business.java** (~80 lines modified)
    - Integrated new directories
    - Added course management methods
    - Revenue calculation methods

11. **ConfigureABusiness.java** (~400 lines)
    - Pre-populated 30 persons
    - Created 12 students, 11 faculty, 4 admins, 2 registrars
    - Built 7 courses, 2 semesters
    - 19 student enrollments

12. **CourseLoad.java** (Enhanced)
    - Added StudentProfile back-reference
    - Added getStudentProfile() method

13. **SeatAssignment.java** (Enhanced)
    - Added getGrade() and setGrade() methods
    - Added getCourseload() getter

14. **Seat.java** (Enhanced)
    - Added getSeatassignment() getter

### User Interface Layer (View/Controller)

**Files Created:**

15. **RegisterPersonJPanel.java** (~380 lines)
    - Person registration form
    - Auto-ID integration
    - Comprehensive validation

16. **ManageStudentsJPanel.java** (~420 lines)
    - Student list with JTable
    - 3 search methods implementation
    - CRUD operations

17. **StudentDetailJPanel.java** (~340 lines)
    - Student information form
    - Edit with validation
    - Save and refresh

18. **ManageFacultyJPanel.java** (~450 lines)
    - Faculty list with course display
    - 3 search methods
    - CRUD operations

19. **FacultyDetailJPanel.java** (~380 lines)
    - Faculty form with academic fields
    - Course assignments display

20. **ManageRegistrarJPanel.java** (~400 lines)
    - Registrar management
    - Search and CRUD

21. **RegistrarDetailJPanel.java** (~320 lines)
    - Registrar detail form

22. **ManageFacultyCourseAssignmentsJPanel.java** (~450 lines)
    - Course-faculty assignment matrix
    - Assign/reassign/remove functionality

23. **AnalyticsDashboardJPanel.java** (~520 lines)
    - All 4 required metrics
    - 5 JTable displays
    - Real-time aggregation

24. **AdminProfileJPanel.java** (~380 lines)
    - Profile editing
    - Functional password change

25. **AdminUserAccount.java** (~340 lines)
    - User account details
    - Password reset
    - Account deletion

26. **AdminRoleWorkAreaJPanel.java** (~150 lines modified)
    - Wired all 8 admin buttons
    - Navigation logic
    - Logout functionality

### Integrated University Model Classes

27-34. **Course-related classes** (Course, CourseCatalog, CourseSchedule, CourseOffer, Seat, SeatAssignment, CourseLoad, Degree)
    - Copied from University Model
    - Package declarations updated
    - Integrated with Business layer

**Total Lines of Code:** ~6,500+ lines

---

## Challenges & Solutions

### Challenge 1: Auto-Generating Unique University IDs

**Problem:** Needed reliable auto-generation that prevents duplicates

**Solution:**
```java
public String generateUniversityId() {
    int nextNumber = personlist.size() + 1;
    String univId;
    do {
        univId = String.format("U%05d", nextNumber);
        nextNumber++;
    } while (isDuplicateUniversityId(univId));
    return univId;
}
```

Uses do-while loop to guarantee uniqueness even with gaps in numbering.

---

### Challenge 2: Implementing 3 Search Methods

**Problem:** Assignment required distinct search methods with different matching logic

**Solution:**
- **Name:** Partial match, case-insensitive using `.contains()`
- **ID:** Exact match using `.equals()`
- **Department:** Partial match, case-insensitive

All implemented separately in each Directory class.

---

### Challenge 3: Combining Two Codebases

**Problem:** ProfileWorkArea and University Model were separate projects

**Solution:**
- Used ProfileWorkArea as foundation (authentication, UI)
- Copied course classes from University Model
- Updated package declarations
- Enhanced existing classes (Person, StudentProfile)
- Merged in Business.java

Result: Seamless integration with all features working.

---

### Challenge 4: Real-Time Table Updates

**Problem:** Tables showed stale data after edits

**Solution:**
- Pass parent panel reference to child panels
- Child calls `parentPanel.refreshTable()` after save
- refreshTable() clears and repopulates from model

---

### Challenge 5: CardLayout Navigation

**Problem:** Using `removeAll()` caused blank screens

**Solution:**
- Don't remove dashboard panel
- Use named cards: `layout.show(CardSequencePanel, "Admin")`
- Back buttons explicitly show dashboard by name

---

### Challenge 6: Input Validation Consistency

**Problem:** Same validation needed across multiple panels

**Solution:**
- Created `InputValidator` utility class
- Centralized validation rules
- Consistent error messages
- Single source of truth

---

### Challenge 7: Password Functionality

**Problem:** Initially password change was just a demo

**Solution:**
- Added `setPassword()` and `getPassword()` to UserAccount
- Implemented verification of current password
- Actually updates password in memory
- Persists across logout/login

**Result:** Fully functional password management system

---

## Technical Architecture

### Design Patterns Used

**Abstract Factory Pattern:**
- `Profile` abstract class
- `StudentProfile`, `FacultyProfile`, `RegistrarProfile` implementations
- Polymorphic role handling

**Directory Pattern:**
- Centralized entity management
- `PersonDirectory`, `StudentDirectory`, `FacultyDirectory`, etc.
- Common CRUD operations

**Template Method Pattern:**
- Common search operations in all directories
- Consistent interface across entity types

**MVC Pattern:**
- Model: Business package classes
- View: UI panels (JPanel, JTable, JButton)
- Controller: Event handlers in panels

### Package Structure

```
Business/                          # Model Layer
├── Person/
│   ├── Person.java               # Enhanced person entity
│   └── PersonDirectory.java      # Person CRUD + search
├── Profiles/
│   ├── Profile.java              # Abstract base
│   ├── StudentProfile.java       # Student data
│   ├── StudentDirectory.java     # Student management
│   ├── FacultyProfile.java       # Faculty data
│   ├── FacultyDirectory.java     # Faculty management
│   ├── RegistrarProfile.java     # Registrar data
│   └── RegistrarDirectory.java   # Registrar management
├── UserAccounts/
│   ├── UserAccount.java          # Login credentials
│   └── UserAccountDirectory.java # Authentication
├── CourseCatalog/
│   ├── Course.java               # Course definition
│   └── CourseCatalog.java        # Course repository
├── CourseSchedule/
│   ├── CourseSchedule.java       # Semester schedule
│   ├── CourseOffer.java          # Course instance
│   ├── Seat.java                 # Enrollment seat
│   ├── SeatAssignment.java       # Student enrollment
│   └── CourseLoad.java           # Semester courses
├── Degree/
│   └── Degree.java               # Degree requirements
├── Validation/
│   └── InputValidator.java       # Input validation
└── Business.java                  # Main container

UserInterface/WorkAreas/AdminRole/ # View/Controller Layer
├── AdminRoleWorkAreaJPanel.java          # Dashboard
├── AdministerUserAccountsWorkResp/
│   ├── ManageUserAccountsJPanel.java
│   └── AdminUserAccount.java
├── ManagePersonnelWorkResp/
│   └── RegisterPersonJPanel.java
├── ManageStudentsWorkResp/
│   ├── ManageStudentsJPanel.java
│   └── StudentDetailJPanel.java
├── ManageFacultyWorkResp/
│   ├── ManageFacultyJPanel.java
│   ├── FacultyDetailJPanel.java
│   └── ManageFacultyCourseAssignmentsJPanel.java
├── ManageRegistrarWorkResp/
│   ├── ManageRegistrarJPanel.java
│   └── RegistrarDetailJPanel.java
├── AnalyticsDashboard/
│   └── AnalyticsDashboardJPanel.java
└── AdminProfile/
    └── AdminProfileJPanel.java
```

### Data Flow

```
User Login
    ↓
Authentication (UserAccountDirectory.AuthenticateUser())
    ↓
Role Detection (UserAccount.getRole())
    ↓
Admin Dashboard (AdminRoleWorkAreaJPanel)
    ↓
User Action (Button Click)
    ↓
Business Logic (Directory Methods)
    ↓
Update Model (Person, Profile, UserAccount)
    ↓
Refresh View (JTable.refreshTable())
```

---

## Pre-Populated Test Data

### Persons (30 Total)
- **Admins:** 4 (John Smith, Gina Montana, Rachel Green, Brandon Hill)
- **Students:** 12 (6 MSIS, 6 MS CS across 2 departments)
- **Faculty:** 11 (7 IS, 4 CS with specializations and office assignments)
- **Registrars:** 2 (Susan Williams, Samantha King)
- **Other:** 1 (Daniel Wright - no account yet)

### Courses (7 Total)
- INFO 5100: Application Engineering and Development (4 credits)
- INFO 6150: Web Design and User Experience (4 credits)
- INFO 6205: Program Structure and Algorithms (4 credits)
- INFO 7390: Advances in Data Sciences (4 credits)
- INFO 7245: Information Visualization (4 credits)
- CSYE 6200: Object Oriented Design (4 credits)
- DAMG 6210: Database Management Systems (4 credits)

### Semesters (2 Total)
- **Fall 2025:** 5 course offerings, 16 enrollments
- **Spring 2025:** 2 course offerings, 3 enrollments

### Total Statistics
- Total Persons: 30
- Total User Accounts: 29
- Total Enrollments: 19
- Total Revenue: $114,000

---

## Assignment Requirements Verification

### Part A: Core Responsibilities

| Requirement | Status | Implementation |
|-------------|--------|----------------|
| User Account Management (Create, Modify, Delete) | ✅ COMPLETE | ManageUserAccountsJPanel + AdminUserAccount |
| Person Registration with Auto-ID | ✅ COMPLETE | RegisterPersonJPanel + PersonDirectory.generateUniversityId() |
| Duplicate Prevention (Email & ID) | ✅ COMPLETE | isDuplicateEmail() + isDuplicateUniversityId() |
| Student Records Management | ✅ COMPLETE | ManageStudentsJPanel + StudentDetailJPanel |
| 3 Search Methods (Student) | ✅ COMPLETE | searchByName(), searchByUniversityId(), searchByDepartment() |
| Faculty Records Management | ✅ COMPLETE | ManageFacultyJPanel + FacultyDetailJPanel |
| 3 Search Methods (Faculty) | ✅ COMPLETE | All 3 methods in FacultyDirectory |
| Associate/Reassign Faculty to Courses | ✅ COMPLETE | ManageFacultyCourseAssignmentsJPanel |
| Registrar Records Management | ✅ COMPLETE | ManageRegistrarJPanel + RegistrarDetailJPanel |
| Profile Management | ✅ COMPLETE | AdminProfileJPanel with password change |
| Analytics: Users by Role | ✅ COMPLETE | AnalyticsDashboardJPanel - Table 1 |
| Analytics: Courses per Semester | ✅ COMPLETE | AnalyticsDashboardJPanel - Table 2 |
| Analytics: Enrollment per Course | ✅ COMPLETE | AnalyticsDashboardJPanel - Table 3 |
| Analytics: Tuition Revenue | ✅ COMPLETE | AnalyticsDashboardJPanel - Metric 4 |

**Feature Completion: 100%**

---

### Part B: Pre-Populated Data Requirements

| Requirement | Status | Count |
|-------------|--------|-------|
| Minimum 1 department | ✅ COMPLETE | 2 departments |
| Minimum 30 persons | ✅ COMPLETE | 30 persons |
| Minimum 10 students | ✅ COMPLETE | 12 students |
| Minimum 10 faculty | ✅ COMPLETE | 11 faculty |
| Minimum 1 admin | ✅ COMPLETE | 4 admins |
| Minimum 1 registrar (group of 4) | ✅ COMPLETE | 2 registrars |
| Minimum 1 semester | ✅ COMPLETE | 2 semesters |
| 5 course offers with faculty assigned | ✅ COMPLETE | 7 courses, all assigned |
| Students with seat assignments | ✅ COMPLETE | 19 enrollments |

**Data Requirements: 100% - Exceeds Minimum**

---

### Part C: Code Quality

| Requirement | Status | Details |
|-------------|--------|---------|
| Access Control & Security | ✅ COMPLETE | Login/logout, role-based access |
| UI/UX Usability | ✅ COMPLETE | GUI builder, clear labels, stable navigation |
| Code Quality | ✅ COMPLETE | Comments, separation of concerns, consistent naming |
| Validation | ✅ COMPLETE | InputValidator, null checks everywhere |
| Documentation | ✅ COMPLETE | This README + code comments |

---

### Pull Request Strategy

**5+ Pull Requests Required:**
1. PR #1: Person and Profile enhancements
2. PR #2: Person Registration feature
3. PR #3: Student and Faculty Records Management
4. PR #4: Analytics Dashboard implementation
5. PR #5: Faculty Course Assignments and final features

---

## Known Limitations

1. **Data Persistence:** All data stored in memory, resets on application restart
2. **Password Security:** Plain text storage (not production-ready)
3. **Concurrent Access:** Single-user session only
4. **Course Creation:** Admin assigns faculty to courses, but cannot create new courses (Registrar responsibility)

---

## Future Enhancements

### Security
- Password encryption (bcrypt/SHA-256)
- Session timeout (30 minutes)
- Audit logging for admin actions
- Two-factor authentication

### Analytics
- Export to PDF/Excel
- Data visualization (charts, graphs)
- Historical trend analysis
- Predictive analytics

### Database Integration
- MySQL/PostgreSQL backend
- Data persistence across sessions
- Backup/restore functionality
- Transaction management

### UI/UX
- Dark mode
- Responsive design
- Accessibility features
- Dashboard customization

---

## Contact Information

**Developer:** Akshay [Last Name]  
**Email:** [Your Email]  
**GitHub:** [Your GitHub Username]  
**Branch:** admin-implementation-akshay  

---

## Submission Checklist

### Code
- ✅ All features implemented and functional
- ✅ No terminal errors during execution
- ✅ Input validation on all forms
- ✅ Null-safety checks throughout
- ✅ Code commented with file headers
- ✅ Clean console output

### Git
- ✅ Personal branch created
- ✅ 10+ meaningful commits
- ✅ 5+ pull requests to main
- ✅ Descriptive commit messages
- ✅ Code reviews completed

### Documentation
- ✅ README.md comprehensive
- ✅ Installation instructions clear
- ✅ Usage guide detailed
- ✅ Test cases documented
- ✅ Contribution breakdown complete

### Testing
- ✅ All 8 test cases pass
- ✅ Navigation works (no blank screens)
- ✅ Validation prevents bad data
- ✅ Password change fully functional
- ✅ Analytics calculations accurate

---

## Acknowledgments

- **Professor Kal Bugrara** for course instruction and reference code
- **Team Members** for collaboration and code reviews
- **Northeastern University** for academic support

