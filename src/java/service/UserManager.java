package service;

import model.User;
import service.exceptions.BeanAlreadyExistException;
import service.exceptions.BeanNotExistException;

/**
 *
 * @author Romain <ro.foncier@gmail.com>
 */
public interface UserManager {

		public void createPersonne(User user) throws BeanAlreadyExistException;
		public void updatePersonne(User user) throws BeanNotExistException;
		public User getPersonne(String id);
}