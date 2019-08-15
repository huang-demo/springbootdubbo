#定义一个pipeline管道

#filebeat 配置文件
filebeat.input:

  # Each - is a prospector. Most options can be set at the prospector level, so
  # you can use different prospectors for various configurations.
  # Below are the prospector specific configurations.
- input_type: log
paths:
  #- /var/log/logstash-tutorial.log   # 之前下载的测试文件
  #- /var/log/*.log
  - E:\logs\springdubbo\admin\*.log
document_type: admin
close_inactive: 1m
scan_frequency: 5s
fields_under_root: true
close_removed: true
tail_files: true
tags: 'admin'

- input_type: log
paths:
  #- /var/log/logstash-tutorial.log   # 之前下载的测试文件
  #- /var/log/*.log
  - E:\logs\springdubbo\sys\*.log
document_type: sys
close_inactive: 1m
scan_frequency: 5s
fields_under_root: true
close_removed: true
tail_files: true
tags: 'sys'

- input_type: log
paths:
  #- /var/log/logstash-tutorial.log   # 之前下载的测试文件
  #- /var/log/*.log
  - E:\logs\springdubbo\user\*.log
document_type: user
close_inactive: 1m
scan_frequency: 5s
fields_under_root: true
close_removed: true
tail_files: true
tags: 'user'

#output.elasticsearch:
  # Array of hosts to connect to.
  #hosts: ["localhost:9200"]
  #pipelines:
  #  - pipeline: "springboot"
  
output.logstash:
  # The Logstash hosts
  hosts: ["localhost:5400"]