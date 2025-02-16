package com.flipkart.business;

import com.flipkart.DAO.FlipFitAdminDAOImplementation;
import com.flipkart.DAO.FlipFitAdminDAOInterface;
import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;

import java.util.List;

public class FlipfitAdminService implements FlipfitAdminInterface{


    private static FlipfitAdmin admin;
    private static boolean loginIN;
    FlipFitAdminDAOInterface dao = new FlipFitAdminDAOImplementation();

    public FlipfitAdminService(){
        // dao implemenation mei fetch data from database and set it up
        admin = dao.getAdminDetails();
        System.out.println(admin.getPassword());
        System.out.println(admin.getAdminId());
        loginIN = false;
    }
    @Override
    public boolean login(int adminId, String password) {
        if(adminId == admin.getAdminId() && password.equals(admin.getPassword())){
            loginIN = true;
            return true;
        }
        loginIN = false;
        return false;
    }

    @Override
    public boolean logout(){
        if (loginIN){
            loginIN = false;
            return true;
        }
        return false;
    }


    @Override
    public FlipfitAdmin viewProfile() {
        if (loginIN){
            return admin;
        }
        return null;
    }

    @Override
    public boolean editProfile(int adminId, String password) {
        if (loginIN && adminId == admin.getAdminId()){
            dao.editProfile(password);
            admin.setPassword(password);
            return true;
        }
        return false;
    }

    @Override
    public List<FlipfitGymOwner> viewGymOwners() {
        // call to database to return the list of ownwers;
        return dao.viewGymOwners();
    }

    @Override
    public List<FlipfitGymCenter> viewGyms() {
        // call to database to return the list of centers
        return dao.viewGyms();
    }

    @Override
    public List<FlipfitCustomer> viewCustomers() {
        // call to database to return customers
        return dao.viewCustomers();
    }

    @Override
    public boolean verifyGym(int gymId) {
        // call to database to get gym Details using id
        // manually view the info
        // user will tell whether to verify or not
        return dao.verifyGym(gymId);
    }

    @Override
    public boolean verifyGymOwner(int gymOwnerId) {
        return dao.verifyGymOwner(gymOwnerId);
    }

    @Override
    public List<FlipfitGymOwner> getUnverifiedGymOwners() {
        return dao.getUnverifiedGymOwners();
    }

    @Override
    public List<FlipfitGymCenter> getUnverifiedGyms() {
        return dao.getUnverifiedGyms();
    }
}
