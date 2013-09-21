package com.sbu.servlet;

import com.sbu.repository.DataRepository;
import com.sbu.repository.MongoInterface;

public class BackEndController {

	protected static BackEndController instance;
	
	protected static String LOCALHOST = "localhost";
	protected static String DATABASE_NAME = "EventFinder";
	protected static String DATA_DIRECTORY = "c:/data";
	
	protected MongoInterface mongoInterface;
	protected DataRepository dataRepo;
	
	public static synchronized BackEndController getInstance()
	{
		if(instance == null)
		{
			instance = new BackEndController();
		}
		
		return instance;
	}
	
	protected BackEndController()
	{
		mongoInterface = new MongoInterface(BackEndController.LOCALHOST, BackEndController.DATABASE_NAME);
		mongoInterface.initializeDB();
		
		dataRepo = new DataRepository(mongoInterface);
		//dataRepo.intializeTables();
	}
	
	public DataRepository getRepo()
	{
		return dataRepo;
	}
}
