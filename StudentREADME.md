Digital University System – Student Use Case
Project Information

Project Title: Access-Controlled Digital University
 System with Role-Based Access

Assignment: Group Assignment 9 – INFO 5100

Institution: Northeastern University

Semester: Fall 2025

Team Member – Student Implementation

Name: Amrin Bushra Taj

NUID: 002565908

Role: Student Use Case Developer

Responsibilities:

Coursework Management

Course Registration and Search

Graduation Audit System

Transcript Review

Profile Management

Financial Management and Tuition Payment System

Table of Contents

Project Overview:

Installation & Setup

Student Features

Usage Instructions

Testing Guide

Code Contributions

Challenges & Solutions

Technical Architecture

Project Overview Purpose:

The Student Use Case of the Digital University System allows students to interact with the academic and administrative systems through a user-friendly, role-based interface. Students can register for courses, manage their academic progress, track graduation requirements, review transcripts, pay tuition, and update personal profiles.

Core Student Features Implemented:

Dynamic course search (3 methods: ID, name, instructor)

Credit limit validation during course enrollment

Automatic GPA and Academic Standing calculation

Real-time graduation audit

Tuition payment and refund automation

Transcript filtering by semester

Profile update with validation

Installation & Setup Prerequisites:

JDK 8+

NetBeans IDE 8.2+

Minimum 2GB RAM

Git (optional, for version control)

Setup Instructions:

Step 1: Clone Repository
git clone [repository-url]
cd DigitalUniversitySystem

Step 2: Open in NetBeans

Launch NetBeans

Go to File → Open Project

Select and open the project folder

Step 3: Build & Run

Right-click project → Clean and Build

Run ProfileWorkAreaMainFrame.java

Login window appears

Step 4: Student Login
Username: student1
Password: ****

Student Features
1. Coursework Management

Location: Student Dashboard → "My Courses"

Features:

View all enrolled courses with course details (ID, Name, Instructor, Credits)

Track submission deadlines and progress

Option to drop courses (tuition refund if applicable)

Total enrolled credits displayed dynamically

Automatic validation: Students cannot exceed 8 credit hours per semester

Technical Logic:

if (student.getTotalCredits() + course.getCreditHours() > 8) {
    JOptionPane.showMessageDialog(null, "Credit limit exceeded!");
}

2. Course Registration & Search

Location: Student Dashboard → "Course Registration"

Features:

View all available courses for the selected semester

3 Search Methods Implemented:

By Course ID: Exact match

By Course Name: Partial match (case-insensitive)

By Instructor Name: Partial match

Dynamic JTable displays filtered search results

Enroll and Drop course options with real-time updates

Credit validation before enrollment

Sample Implementation:

public Course searchById(String id) { ... }
public List<Course> searchByName(String name) { ... }
public List<Course> searchByInstructor(String instructor) { ... }


Assignment Requirement Met:

"Implement 3 different search methods for course registration (ID, name, instructor). Populate results dynamically in the same JTable."

3. Graduation Audit

Location: Student Dashboard → "Graduation Audit"

Features:

Tracks total completed and in-progress credits

Displays required and earned credits (MSIS = 32 total)

Checks if student completed INFO 5100 (Core Course)

Displays status:

✅ Ready to Graduate if total ≥ 32 and INFO 5100 completed

❌ Not Eligible Yet otherwise

Technical Logic:

if (creditsCompleted >= 32 && hasCompletedCoreCourse("INFO 5100")) {
    statusLabel.setText("Eligible for Graduation ✅");
} else {
    statusLabel.setText("Not Ready for Graduation ❌");
}

4. Transcript Review

Location: Student Dashboard → "Transcript"

Features:

Displays transcript records semester-wise

Dropdown to filter by semester (e.g., Fall 2025, Spring 2025)

GPA Calculation (Term & Overall)

Automatic Academic Standing display:

Good Standing: Term GPA ≥ 3.0 and Overall GPA ≥ 3.0

Academic Warning: Term GPA < 3.0

Academic Probation: Overall GPA < 3.0

Grade Mapping:

Grade	Points
A	4.0
A−	3.7
B+	3.3
B	3.0
B−	2.7
C+	2.3
C	2.0
C−	1.7
F	0.0

Sample Calculation:

termGPA = totalQualityPoints(term) / totalCredits(term);
overallGPA = totalQualityPoints(allTerms) / totalCredits(allTerms);

5. Profile Management

Location: Student Dashboard → "My Profile"

Features:

View and update personal details: Name, Email, Phone, Address

Validations for all fields

Functional password change

Profile updates persist immediately

Validation Logic:

if (!InputValidator.isValidEmail(email)) {
    JOptionPane.showMessageDialog(null, "Invalid Email Format!");
}

6. Financial Management

Location: Student Dashboard → "Pay Tuition"

Features:

Displays total tuition owed for enrolled courses

Tuition auto-calculated: Credit Hours × $1500

Option to pay tuition and view payment history

Block transcript view if tuition unpaid

Refund automatically applied when course dropped after payment

Dialog box if balance = 0: “No balance to pay.”

Sample Logic:

if (student.getBalance() <= 0) {
    JOptionPane.showMessageDialog(null, "No balance to pay!");
} else {
    student.payTuition(amount);
}


Assignment Requirement Met:

"Student will not be able to see transcript until tuition is paid. Refund tuition if dropped after payment."

Usage Instructions
Accessing Student Features

Login as student

Dashboard displays 6 main panels:

My Courses

Course Registration

Graduation Audit

Transcript

Pay Tuition

My Profile

Example Workflow: Course Enrollment

Go to Course Registration

Select semester (e.g., Fall 2025)

Use Search by Course ID or Instructor Name

Select course and click Enroll

Credit validation check occurs

Course added to "My Courses" panel

Tuition automatically billed

Testing Guide:

Test Case 1: Credit Limit Enforcement

Steps:

Enroll in multiple courses

Exceed 8 credits

Expected: Alert “Credit limit exceeded!”

Test Case 2: Course Search

Steps:

Search by ID → Enter INFO 5100

Search by Name → Enter Data

Search by Instructor → Enter Smith
Expected: Each filter returns accurate results.

Test Case 3: GPA Calculation

Steps:

Add sample grades for courses

Check term GPA & overall GPA displayed
Expected: Matches manual calculation.

Test Case 4: Tuition Payment

Steps:

Go to Pay Tuition → Click Pay

Check balance updates

View Payment History
Expected: “No balance” message when fully paid.

Test Case 5: Graduation Audit

Steps:

Complete 32 credits including INFO 5100

Open Graduation Audit
Expected: “Eligible for Graduation ✅”

Test Case 6: Transcript Access Control

Steps:

Unpaid tuition → Try to open transcript
Expected: Access denied message

Pay tuition → Retry
Expected: Transcript visible

Code Contributions

Key Classes Modified/Created:

StudentProfile.java – added tuition, GPA, and audit tracking

TranscriptJPanel.java – GPA logic and standing calculation

CourseRegistrationJPanel.java – three search methods and credit validation

FinancialManagementJPanel.java – tuition payment and refund logic

GraduationAuditJPanel.java – graduation eligibility logic

InputValidator.java – enhanced with student-side validation

Tech Stack:

Java Swing (UI)

Object-Oriented Data Model

In-memory data persistence

MVC architecture pattern

Data Flow Summary:

Student → CourseDirectory → CourseOffer → SeatAssignment → Transcript
                      ↘ TuitionManager → PaymentHistory