/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Validation;

public class InputValidator {

    /**
     * Validate email format Checks for basic email structure: text@text.text
     *
     * @param email Email address to validate
     * @return true if valid email format, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Basic email regex pattern
        // Matches: username@domain.com, first.last@domain.co.uk, etc.
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Validate if email is from Northeastern domain
     *
     * @param email Email address to validate
     * @return true if email ends with @northeastern.edu
     */
    public static boolean isNortheasternEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.toLowerCase().endsWith("@northeastern.edu");
    }

    /**
     * Validate phone number format Accepts various formats: 6171234567,
     * 617-123-4567, (617) 123-4567, 617.123.4567
     *
     * @param phone Phone number to validate
     * @return true if valid 10-digit US phone number, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // Phone is optional, so empty is valid
        }

        // Remove common separators and spaces
        String cleanPhone = phone.replaceAll("[\\s\\-\\.\\(\\)]", "");

        // Check if it's all digits
        if (!cleanPhone.matches("\\d+")) {
            return false; // Contains non-digit characters
        }

        // US phone numbers should be exactly 10 digits
        return cleanPhone.length() == 10;
    }

    /**
     * Format phone number to standard format (617-123-4567)
     *
     * @param phone Phone number to format
     * @return Formatted phone number or original if invalid
     */
    public static String formatPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return phone;
        }

        // Clean the phone number
        String cleanPhone = phone.replaceAll("[\\s\\-\\.\\(\\)]", "");

        // Format to XXX-XXX-XXXX if valid
        if (cleanPhone.matches("\\d{10}")) {
            return cleanPhone.substring(0, 3) + "-"
                    + cleanPhone.substring(3, 6) + "-"
                    + cleanPhone.substring(6, 10);
        }

        return phone; // Return original if can't format
    }

    /**
     * Validate that a string is not null or empty
     *
     * @param value String to validate
     * @return true if string has content, false if null or empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validate that a string contains only letters and spaces
     *
     * @param value String to validate
     * @return true if contains only letters and spaces
     */
    public static boolean isAlphabetic(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return value.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Validate password strength Password must be at least 4 characters long
     *
     * @param password Password to validate
     * @return true if password meets minimum requirements
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Minimum 4 characters (can be made more strict if needed)
        return password.length() >= 4;
    }

    /**
     * Get a user-friendly error message for invalid email
     *
     * @param email The invalid email
     * @return Error message with suggestions
     */
    public static String getEmailErrorMessage(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email address is required.";
        }

        if (!email.contains("@")) {
            return "Email must contain '@' symbol.\nFormat: username@domain.com";
        }

        if (!email.contains(".")) {
            return "Email must contain a domain extension.\nFormat: username@domain.com";
        }

        return "Please enter a valid email address.\n"
                + "Format: username@domain.com\n"
                + "For Northeastern: use @northeastern.edu";
    }

    /**
     * Get a user-friendly error message for invalid phone
     *
     * @param phone The invalid phone number
     * @return Error message with format examples
     */
    public static String getPhoneErrorMessage(String phone) {
        return "Please enter a valid 10-digit US phone number.\n"
                + "Accepted formats:\n"
                + "  • 6171234567\n"
                + "  • 617-123-4567\n"
                + "  • (617) 123-4567\n"
                + "  • 617.123.4567";
    }
}
