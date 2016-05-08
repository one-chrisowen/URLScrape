package urlscrape.mainApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import urlscrape.dataListeners.IDataListener;
import urlscrape.mainApplication.DataBroadcaster;

@RunWith(MockitoJUnitRunner.class)
public class DataBroadcasterTest {
	
	@Mock
	private IDataListener listener;
	
	private DataBroadcaster testSubject;

	@Before
	public void setUp() throws Exception {
		testSubject = new DataBroadcaster();
		testSubject.addListener(listener);
	}

	@Test
	public void testAddList() throws Exception {
		
		testSubject.addList("TestListID");
		
		Mockito.verify(listener).addList("TestListID");
	}
	
	@Test
	public void testAddToList() throws Exception {
		
		testSubject.addToList("TestID", "testString");
		
		Mockito.verify(listener).addToList("TestID", "testString");
	}

	@Test
	public void testAddObject() throws Exception {
		
		testSubject.addObject("TestObjectID");
		
		Mockito.verify(listener).addObject("TestObjectID");
	}

	@Test
	public void testAddToObject() throws Exception {
		
		testSubject.addToObject("TestObjID", "testString");
		
		Mockito.verify(listener).addToObject("TestObjID", "testString");
	}

}
