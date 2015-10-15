package tcp.server

class IndexController {

    def index() {

        /*response.sendError(404)
        return*/
/************
***/
println 0
        def requestSocket = new Socket('185.4.75.89', 17003)//cvoz
        //def requestSocket = new Socket('84.52.127.225', 17003)//audi
    	//def requestSocket = new Socket('84.52.127.229', 17003)//alpha
    	//def requestSocket = new Socket('localhost', 17003)
println 1
    	//def r = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
        def r = requestSocket.getInputStream()
println 2
        //def w = new BufferedWriter(new OutputStreamWriter(requestSocket.getOutputStream()))
    	def w = requestSocket.getOutputStream()
println 3
        w.write([0,15,49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53] as byte[])
        //w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
println 4
        w.flush()
println 5

        byte[] buffer = new byte[4]
println 6
    	def reply = r.read(buffer,0,4)
        println ('r1:'+reply)
        println ('b1:'+buffer.toString())
println 7
        if (reply>0){
println 8
            w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
            w.flush()

            buffer = new byte[4]
            reply = r.read(buffer,0,4)

            println ('r2:'+reply)
            println ('b2:'+buffer.toString())
            w.flush()
        }
        w.close()
    	render buffer.toString()
/***
************/
    }
/*******
    def testing() {
        def testcount = (params.id.isInteger()?params.id.toInteger():10)
        def th=new Thread()
        th.start{
            def i = 0
            testcount.times{
                th.sleep(2*1000)
                def newth=new Thread()
                newth.start{
                    try {
                        def y = i
                        newth.sleep(3*1000)
                        def requestSocket = new Socket('84.52.127.229', 17003)
                        def r = requestSocket.getInputStream()
                        def w = requestSocket.getOutputStream()

                        w.write([0,15,49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, (48+y)] as byte[])
                        w.flush()

                        byte[] buffer = new byte[4]
                        def reply = r.read(buffer,0,4)

                        newth.sleep(7*1000)

                        w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
                        w.flush()

                        buffer = new byte[4]
                        reply = r.read(buffer,0,4)

                        newth.sleep(10*1000)

                        w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
                        w.flush()

                        buffer = new byte[4]
                        reply = r.read(buffer,0,4)

                        newth.sleep(30*1000)

                        w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
                        w.flush()

                        buffer = new byte[4]
                        reply = r.read(buffer,0,4)

                        newth.sleep(10*1000)

                        w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
                        w.flush()

                        buffer = new byte[4]
                        reply = r.read(buffer,0,4)

                        newth.sleep(10*1000)

                        w.write([0,0,0,0,0,0,0,61,8, 1, 0, 0, 1, 63, -101, 48, 99, 24, 0, 18, 18, -39, -101, 35, -76, -21, 53, 0, 0, 0, -94, 4, 0, 1, 0, 11, 7, 0, 3, -1, 0, 1, 0, 2, 0, 21, 55, 70, 38, 91, 1, 3, 93, 97, -86, 66, 73, -104, 67, 16, 24, 1, 92, 0, 0, 0, 10, 0, 1,0,0,134,18] as byte[])
                        w.flush()

                        buffer = new byte[4]
                        reply = r.read(buffer,0,4)

                        newth.sleep(60*1000)
                        w.flush()
                        w.close()
                    } catch(Exception e) {
                        log.debug(e.toString())
                    }
                }
                ++i
            }
        }
        render params.id
    }
*****/
}