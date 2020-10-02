# Spring Quartz Demo

## mvn clean spring-boot:run

## Quartz creates a new Job instance every time

```
Main Thread exits: 1, main
SampleJob: Fri Oct 01 15:22:33 CST 2020, huaminglin.demo.quartz.spring.SampleJob@63d049c9
SampleJob: Fri Oct 01 15:23:32 CST 2020, huaminglin.demo.quartz.spring.SampleJob@25852833
```

## quartz-2.3.2.jar!/org/quartz/impl/jdbcjobstore/tables_mysql_innodb.sql

## Start two app nodes

```
15:33:55.284 [main] INFO  h.d.quartz.spring.SpringQuartzDemo - Started SpringQuartzDemo in 2.028 seconds (JVM running for 2.46)
Main Thread exits: 1, main
SampleJob: Fri Oct 02 15:33:55 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@49ed5745, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:34:54 GMT 2020
SampleJob: Fri Oct 02 15:36:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@2c1881c1, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:37:03 GMT 2020
SampleJob: Fri Oct 02 15:38:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@5d0b2d6e, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:39:03 GMT 2020

SampleJob: Fri Oct 02 15:41:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@8b82259, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:42:03 GMT 2020
SampleJob: Fri Oct 02 15:43:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@27dadacc, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:44:03 GMT 2020
SampleJob: Fri Oct 02 15:45:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@536362e9, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:46:03 GMT 2020
SampleJob: Fri Oct 02 15:46:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@7b3c0a8b, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:47:03 GMT 2020
SampleJob: Fri Oct 02 15:48:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@1541ae0b, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:49:03 GMT 2020
SampleJob: Fri Oct 02 15:50:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@1ae6c985, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:51:03 GMT 2020

SampleJob: Fri Oct 02 15:52:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@12f1c284, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:53:03 GMT 2020

SampleJob: Fri Oct 02 15:55:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@4742c705, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:56:03 GMT 2020


SampleJob: Fri Oct 02 15:39:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@6b627009, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:42:00 GMT 2020
SampleJob: Fri Oct 02 15:42:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@39559c4, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:45:00 GMT 2020
SampleJob: Fri Oct 02 15:51:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@63e27009, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:54:00 GMT 2020
SampleJob: Fri Oct 02 15:54:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@2498992e, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:57:00 GMT 2020
```


```
15:34:05.203 [main] INFO  h.d.quartz.spring.SpringQuartzDemo - Started SpringQuartzDemo in 2.081 seconds (JVM running for 2.508)
Main Thread exits: 1, main
SampleJob: Fri Oct 02 15:34:05 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@705e61f, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:35:03 GMT 2020
SampleJob: Fri Oct 02 15:35:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@6b80ecf9, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:36:03 GMT 2020

SampleJob: Fri Oct 02 15:37:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@71f86bac, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:38:03 GMT 2020
SampleJob: Fri Oct 02 15:39:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@3fcfcd76, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:40:03 GMT 2020
SampleJob: Fri Oct 02 15:40:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@5e30d01a, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:41:03 GMT 2020
SampleJob: Fri Oct 02 15:42:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@472ab1d0, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:43:03 GMT 2020
SampleJob: Fri Oct 02 15:44:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@2c1881c1, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:45:03 GMT 2020

SampleJob: Fri Oct 02 15:47:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@6b627009, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:48:03 GMT 2020

SampleJob: Fri Oct 02 15:49:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@247a15c2, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:50:03 GMT 2020
SampleJob: Fri Oct 02 15:51:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@56cd6e4e, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:52:03 GMT 2020
SampleJob: Fri Oct 02 15:53:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@3e0291d8, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:54:03 GMT 2020
SampleJob: Fri Oct 02 15:54:03 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@70353ec4, Trigger 'DEFAULT.trigger01':  triggerClass: 'org.quartz.impl.triggers.SimpleTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:55:03 GMT 2020

SampleJob: Fri Oct 02 15:36:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@19379923, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:39:00 GMT 2020
SampleJob: Fri Oct 02 15:45:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@5d0b2d6e, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:48:00 GMT 2020
SampleJob: Fri Oct 02 15:48:00 GMT 2020, huaminglin.demo.quartz.spring.SampleJob@5f295d15, Trigger 'DEFAULT.trigger02':  triggerClass: 'org.quartz.impl.triggers.CronTriggerImpl calendar: 'null' misfireInstruction: 0 nextFireTime: Fri Oct 02 15:51:00 GMT 2020

```

From the log, it seems both CronTriggerImpl and SimpleTriggerImpl don't have duplicated triggered jobs.

Question: what kind of problem does quartz cluster solve?
