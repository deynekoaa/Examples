package ru.mephi.education.xml.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ru.mephi.education.xml.jaxb.Company.Staff;

public class JAXBExample {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Company company = (Company) unmarshaller
				.unmarshal(new File("staff.xml"));
		System.out.println(company);

		Staff staff = new Staff();
		staff.setFirstname("Alexandr");
		staff.setLastname("Boruchinkin");
		staff.setId((short) 3001);
		staff.setNickname("Boruch");
		staff.setSalary(30000);

		company.getStaff().remove(1);
		company.getStaff().add(staff);

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(company, new File("new_jaxb_stuff.xml"));
	}

}
