import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class test {
	public static void main(String[] args) {
		LocalDate birthdate = LocalDate.parse("1999-01-31");
		Timestamp birthday = Timestamp.valueOf(birthdate.atStartOfDay());
		System.out.println(birthday);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm");
		String day = sdf.format(birthday);
		System.out.println(day);
	}
}
