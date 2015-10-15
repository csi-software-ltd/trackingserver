package tcp.server

class Trsession {

  static constraints = {
  }
  static mapping = {
    version false
  }

	Long id
	String ip_connection_id
	String imei
	Date start_session = new Date()

}