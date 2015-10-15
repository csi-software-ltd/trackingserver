// Place your Spring DSL code here
import tcp.server.ByteArrayNovacomGNSExtSerializer
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer
beans = {
        xmlns integration:'http://www.springframework.org/schema/integration'
        xmlns ip:'http://www.springframework.org/schema/integration/ip'

        bArDeserializer (ByteArrayNovacomGNSExtSerializer,2){
        }
        rawSerializer (ByteArrayRawSerializer){
        }

        ip.'tcp-connection-factory'(
                id: "tcpServer",
                type: "server",
                host: 'localhost',
                port: "17003",
                'using-nio':true,
                'single-use':false,
                'so-timeout':300000,
                deserializer: "bArDeserializer",
                serializer: "rawSerializer"
        )

        ip.'tcp-inbound-gateway'(
                id: "gatewayTcpServer",
                'connection-factory': "tcpServer",
                'request-channel': "toTcpServerService",
                'error-channel': "tcpServerErrorChannel"
        )

        integration.'channel'(
                id: "toTcpServerService"
        )

        integration.'service-activator'(
                'input-channel': "toTcpServerService",
                'expression': "@tcpserverService.trackhandling(payload, headers.ip_connectionId, @tcpServer)"
        )

        integration.'transformer'(
                'id': "tcpServerErrorHandler",
                'input-channel': "tcpServerErrorChannel",
                'expression': "payload.failedMessage.payload + ':' + payload.cause.message"
        )
}