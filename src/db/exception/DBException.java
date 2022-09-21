package db.exception;

@SuppressWarnings("serial")
public class DBException extends RuntimeException{
   
	public DBException(String MSG) {
		super(MSG);
	}
}
