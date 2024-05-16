package com.untucapital.usuite.utg.dto;

    public class Email {
        private String recipient;
        private String subject;
        private String message;

        // Constructor, getters, and setters

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipients) {
            this.recipient = recipient;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

//}
