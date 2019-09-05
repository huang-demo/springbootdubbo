input {
    beats {
        host => "127.0.0.1"
        port => 5411
    }
}

filter {
    grok {
        match => {
            "message" => "%{TIMESTAMP_ISO8601:request_time}\s%{DATA:thread}\s%{DATA:traceId}\s+%{LOGLEVEL:level}\s+%{DATA:class}\s+%{GREEDYDATA:rest}"
        }
    }
	#mutate{
    #    split=>["message","|"]#按|进行split切割message
    #    add_field=>{
    #      "request_time"=>"%{[message][0]}"
    #    }add_field=>{
    #      "thread"=>"%{[message][1]}"
    #    }add_field=>{
    #      "traceId"=>"%{[message][2]}"
    #    }add_field=>{
    #      "level"=>"%{[message][3]}"
    #    }
    #  }
    date {
        match => [ "request_time" , "yyyy-MM-dd HH:mm:ss.SSS" ]
        target => "@timestamp"
    }
    if [level] == "ERROR" {
        mutate {
            add_field => { "@alertMsg[className]" => "%{class}"}
            add_field => { "@alertMsg[body]" => "%{rest}"}
            add_field => { "@alertMsg[level]" => "%{level}"}
            add_field => { "@alertMsg[requestTime]" => "%{request_time}"}
            add_field => { "@alertMsg[module]" => "%{[fields][module]}"}

        }
    }
}


output {
    elasticsearch {
        hosts  => ["192.168.131.105:9200","192.168.131.105:8200"]
        index  => "%{[fields][log_topic]}"
        #user  => "elastic"
        #password => "changeme"
    }
    if [level] == "ERROR" {
        rabbitmq {
           host => "192.168.131.102"
           port => "5672"
           vhost => "dev"
           exchange => "MESSAGE.SYSLOG.EXCHANGE"
           exchange_type => "fanout"
           user => "guest"
           password => "guest"
           codec => plain {
             format => "%{@alertMsg}"
           }
        }
    }
}

