package tcp.server

class TcpserverService {
	static transactional = false

	def trackhandling(payload, ip_connectionId, connFactory) {

		def session = Trsession.findByIp_connection_id(ip_connectionId)
		if (!session) {
			try {
				Trsession.withNewSession{ s ->
					new Trsession(ip_connection_id:ip_connectionId,imei:(new String(payload)?:'')).save(flush:true)
				}
				return ([1] as byte[])
			}catch(Exception e) {
				log.error('error save session for connectionId:'+ip_connectionId+'\nimei: '+(new String(payload)?:'')+'\n'+e.toString())
				connFactory.closeConnection(ip_connectionId)
				return null
			}
		} else {
			try {
				def avlData = payload.encodeAsHex()
				Trackingdata.withNewSession{ s ->
					new Trackingdata(imei:session.imei,x:Long.parseLong(avlData[22..29], 16),y:Long.parseLong(avlData[30..37], 16),tracktime:new Date(Long.parseLong(avlData[4..19], 16)),speed:Integer.parseInt(avlData[48..51],16),kurs:Integer.parseInt(avlData[42..45],16)).save(flush:true)
				}
				return ([0,0,0,1] as byte[])
			} catch(Exception e) {
				log.error('error save data for session:'+session.id+'\nhexstring: '+payload.encodeAsHex()+'\n'+e.toString())
				return ([0,0,0,0] as byte[])
			}
		}

	}
}