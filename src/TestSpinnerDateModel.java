import java.sql.Date;

import javax.swing.SpinnerDateModel;



public class TestSpinnerDateModel {

	public static void main(String[] args) {
		SpinnerDateModel model = new SpinnerDateModel();
		
		System.out.println(model.getDate().getMonth());
		System.out.println(model.getDate().getDate() + "-"+(model.getDate().getMonth()+1)+"-"+(model.getDate().getYear()+1900));
		String date = "02/02/2020";
		Date d = new Date((model.getDate().getYear()), (model.getDate().getMonth()), model.getDate().getDate());
		System.out.println(d);
		
		Date dsql = new Date(120, 5, 23);
		d = dsql;
		System.out.println(d);
	}

}
