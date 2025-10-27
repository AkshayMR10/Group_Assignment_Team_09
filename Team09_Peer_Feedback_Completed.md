# Team 09 — Peer Feedback (INFO 5100 • Fall 2025)
**Project:** Digital University — Role-Based Access Control    
**Date:** 2025-10-26

> Summary: Good collaboration and delivery. The system demoed smoothly with strong validation, clean navigation, and comprehensive documentation.

---

## Rating Scale (used below)
- **5 — Exceptional** · 4 — Strong · 3 — Meets · 2 — Limited · 1 — Insufficient

---

## Section A — Peer Reviews

### A1) Feedback for **Akshay** (Administrator)
**Ratings (1–5)**  
- Collaboration & Communication: **5/5**  
- Ownership & Reliability: **5/5**  
- Technical Contribution / Role Mastery: **5/5**  
- Quality & Testing: **5/5**  
- Documentation & Handover: **5/5**  
- Leadership & Initiative: **5/5**  

**Overall:** **5/5**

**Evidence / Highlights**  
- Delivered **Admin dashboard** end‑to‑end: User Accounts, Person Registration (auto‑ID, duplicate checks), Student/Faculty/Registrar management (3 search methods each).  
- Implemented **Analytics Dashboard (4 metrics)** with real‑time refresh.  
- Drove **validation strategy** (email/phone/password) and ensured null‑safety across forms.

**Strengths**  
- Clear architectural thinking; separated model vs UI cleanly.  
- Proactive in reviews and unblocking others; consistent code quality and naming.  
- Thorough testing; tables always refreshed correctly after mutations.

---

### A2) Feedback for **Amrin** (Student)
**Ratings (1–5)**  
- Collaboration & Communication: **5/5**  
- Ownership & Reliability: **5/5**  
- Technical Contribution / Role Mastery: **5/5**  
- Quality & Testing: **5/5**  
- Documentation & Handover: **5/5**  
- Leadership & Initiative: **5/5**  

**Overall:** **5/5**

**Evidence / Highlights**  
- Implemented **Course Registration** with 3 search methods (ID/Name/Instructor) and robust **credit‑limit checks (≤8 credits)**.  
- Built **Coursework**, **Graduation Audit** (≥32 credits + INFO 5100 core), **Transcript** (term/cumulative GPA & standing), and **Financials** view.  
- Added concise **Student README** and helpful in‑UI cues for validation.

**Strengths**  
- User‑friendly flows; great attention to empty‑state handling and error messages.  
- Reliable delivery; features worked first‑time in demo with clean navigation.

---

### A3) Feedback for **Anushika** (Registrar)
**Ratings (1–5)**  
- Collaboration & Communication: **5/5**  
- Ownership & Reliability: **5/5**  
- Technical Contribution / Role Mastery: **5/5**  
- Quality & Testing: **5/5**  
- Documentation & Handover: **5/5**  
- Leadership & Initiative: **5/5**  

**Overall:** **5/5**

**Evidence / Highlights**  
- Delivered **CourseOffer management**: create/modify offers, capacity/room/time, and **assign faculty** with confirmations.  
- Implemented **admin‑side enrollment** and reconciliation/report views aligned with analytics.  
- Coordinated schema details with Admin and Faculty to keep data consistent across roles.

**Strengths**  
- Strong systems thinking; ensured Registrar updates propagated cleanly to other panels.  
- Excellent QA—caught edge cases (capacity, duplicate assignments) early.

---

### A4) Feedback for **Sriya** (Faculty)
**Ratings (1–5)**  
- Collaboration & Communication: **5/5**  
- Ownership & Reliability: **5/5**  
- Technical Contribution / Role Mastery: **5/5**  
- Quality & Testing: **5/5**  
- Documentation & Handover: **5/5**  
- Leadership & Initiative: **5/5**  

**Overall:** **5/5**

**Delivered/Highlights**  
1) **Manage Courses** per semester with capacity/enrolled and clean table refresh.  
2) **Manage Student Profiles (Roster)** pulling from occupied seats; **Gradebook dialog** with seeded rows, recalculation, and **Post Final** writing GPA to `SeatAssignment`.  
3) **Performance Reports** (distribution, average, enrollment) with **CSV export**; **Tuition Insight** summary by semester; **My Profile** with validation and read‑only name/ID.

**Strengths**  
- Robust orchestration across Faculty flows—kept Gradebook, Reports, and Tuition views consistent with roster and enrollment changes.
- High reliability under edge cases—validated selections/inputs and prevented nulls/duplicates, leading to stable, crash-free demos.

## Final Comment
**Good team performance.** Every member delivered with maintainable code, stable demos, and clear documentation. The project meets/exceeds rubric expectations for access control, data seeding, usability, and reporting.
