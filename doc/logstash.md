#配置文件

input{
  beats{
    host=>"127.0.0.1"
    port=>5400
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
    #      "create"=>"%{[message][0]}"
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
            add_field => { "@alertMsg[title]" => "%{class}"}
            add_field => { "@alertMsg[message]" => "%{rest}"}
            add_field => { "@alertMsg[type]" => "%{level}"}
            add_field => { "@alertMsg[timestamp]" => "%{request_time}"}

        }
    }
}
output{
  elasticsearch{
    hosts=>[
      "127.0.0.1:9200"
    ]index=>"dubbo-%{+YYYY.MM.dd}"#自定义的名字
  }stdout{
    codec=>rubydebug
  }
}


#启动(配置文件自动更新)
bin/logstash -f xxx.conf --config.reload.automatic
