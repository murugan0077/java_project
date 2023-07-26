package com.zoho.Mbank;

    interface CustomerMenuInterface
    {
        void showProfile(int uid);
        void checkBalance(int uid);
        void transferAmount(int uid);
        void showAccountHistory(int uid);
    }

    interface StaffInterface extends CustomerMenu
    {
       
        void accessCustomer();
        void depositAmount();
        void withdrawAmount();

    }
    interface adminInterface extends StaffInterface
    {
    void approveStaff();
    }