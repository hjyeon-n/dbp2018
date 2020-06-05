package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Client;
import model.dao.AnimalDAO;

import model.Donation;
import model.dao.DonationDAO;

public class DonationManager {
   private static DonationManager donMan = new DonationManager();
   private DonationDAO DonationDAO;
   private AnimalDAO AnimalDAO;

   private DonationManager() {
      try {
         DonationDAO = new DonationDAO();
         AnimalDAO = new AnimalDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static DonationManager getInstance() {
      return donMan;
   }
   
   public boolean existingAnimal(String animalID) throws SQLException, ExistingAnimalException {
      return DonationDAO.existingAnimal(animalID);
   }
   
   public int update(String animalID) throws SQLException {
      return DonationDAO.update(animalID);
   }
   
   public List<Integer> selectAnimalNum(String date) throws SQLException {
      return DonationDAO.selectAnimalNum(date);
   }
   
   public List<Integer> selectDon(String date) throws SQLException {
      return DonationDAO.selectDon(date);
   }
   
   public int totalDonUpdate(String date) throws SQLException {
      return DonationDAO.totalDonUpdate(date);
   }
   
   public int updateTerm() throws SQLException {
      return DonationDAO.updateTerm();
   }
   
   public int create(String animalID, Client client) throws SQLException, ExistingAnimalException {
      return DonationDAO.create(animalID, client);
   }
   
   public Donation findClient(String animalID) throws SQLException, ExistingAnimalException {
      return DonationDAO.findClient(animalID);
   }
}