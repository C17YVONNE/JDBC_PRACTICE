package jp.dcnet.www;

import java.util.List;

public class PersonService {

	public void addPerson(List<PersonObj> list) {
		
		PersonDao dao = new PersonDao();
		dao.addPerson(list);

	}

}
