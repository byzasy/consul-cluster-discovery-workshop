#Steps

This consul server starts with ui. 
nohup ./consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1 2>&1 &

Ui address is 192.168.155.33:8500/ui for the below congiration.

PreConfiguration:
1. Decide network adapter. 
   vmnet1    Link encap:Ethernet  HWaddr 00:50:56:c0:00:01  
          inet addr:192.168.155.1  Bcast:192.168.155.255  Mask:255.255.255.0
          inet6 addr: fe80::250:56ff:fec0:1/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:19936 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

2. Decide ip address of consul clients. In this example, we have two machine. So, i should have 2 ips. <decided_ip_of_machine_1:192.168.155.33> <decided_ip_of_machine_2:192.168.155.35>
   We prefer vmnet1, and 192.168.155.33/192.168.155.35

Run Commands:

1. <sudo> vagrant up
2. <sudo> vagrant ssh n1
3. n1> consul agent -server -ui -bootstrap-expect=1 -data-dir=/tmp/consul -node=agent-one -client=0.0.0.0 -bind=<decided_ip_of_machine_1> -enable-script-checks=true -config-dir=/etc/consul.d
4. <sudo> vagrant ssh n2
5. n2> consul agent -data-dir=/tmp/consul -node=agent-two -client=0.0.0.0 -bind=<decided_ip_of_machine_2> -enable-script-checks=true -config-dir=/etc/consul.d
6. <sudo> vagrant ssh n1
7. n1> consul join <decided_ip_of_machine_2>
8. n1> consul members

Microservices Consul Configration

[Student Service]
--------------------------------------------------------------------------
bootstrap.yml
----------------------
spring:
  application:
    name: student-service
  cloud:
    consul:
      host: 192.168.155.33
      port: 8500
      discovery:
        instance-id: ${spring.application.name}:${random.value}

application.yml
----------------------
server:
  port: 9098

management:
  security:
    enabled: false

[School Service]
--------------------------------------------------------------------------
bootstrap.yml
----------------------
spring:
  application:
    name: student-service
  cloud:
    consul:
      host: 192.168.155.35
      port: 8500
      discovery:
        instance-id: ${spring.application.name}:${random.value}

application.yml
----------------------
server:
  port: 8098

management:
  security:
    enabled: false



 



