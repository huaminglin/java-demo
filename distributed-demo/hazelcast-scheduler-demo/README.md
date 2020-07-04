# Demo Hazelcast Scheduler

## Hazelcast Client can only submit job to the cluster

```exception
Caused by: com.hazelcast.nio.serialization.HazelcastSerializationException: java.lang.ClassNotFoundException: huaminglin.demo.hazelcastclient.scheduler.Task
	at com.hazelcast.internal.serialization.impl.JavaDefaultSerializers$JavaSerializer.read(JavaDefaultSerializers.java:87) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.JavaDefaultSerializers$JavaSerializer.read(JavaDefaultSerializers.java:76) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.StreamSerializerAdapter.read(StreamSerializerAdapter.java:48) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.AbstractSerializationService.readObject(AbstractSerializationService.java:268) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:567) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.scheduledexecutor.impl.NamedTaskDecorator.readData(NamedTaskDecorator.java:89) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.readInternal(DataSerializableSerializer.java:160) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.read(DataSerializableSerializer.java:106) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.read(DataSerializableSerializer.java:51) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.StreamSerializerAdapter.read(StreamSerializerAdapter.java:48) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.AbstractSerializationService.readObject(AbstractSerializationService.java:268) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.ByteArrayObjectDataInput.readObject(ByteArrayObjectDataInput.java:567) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.scheduledexecutor.impl.ScheduledRunnableAdapter.readData(ScheduledRunnableAdapter.java:106) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.readInternal(DataSerializableSerializer.java:160) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.read(DataSerializableSerializer.java:106) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.DataSerializableSerializer.read(DataSerializableSerializer.java:51) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.StreamSerializerAdapter.read(StreamSerializerAdapter.java:48) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.internal.serialization.impl.AbstractSerializationService.toObject(AbstractSerializationService.java:187) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.task.scheduledexecutor.ScheduledExecutorSubmitToPartitionMessageTask.prepareOperation(ScheduledExecutorSubmitToPartitionMessageTask.java:44) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.task.AbstractPartitionMessageTask.processInternal(AbstractPartitionMessageTask.java:45) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.task.AbstractAsyncMessageTask.processMessage(AbstractAsyncMessageTask.java:71) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.task.AbstractMessageTask.initializeAndProcessMessage(AbstractMessageTask.java:145) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.client.impl.protocol.task.AbstractMessageTask.run(AbstractMessageTask.java:108) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationservice.impl.OperationRunnerImpl.run(OperationRunnerImpl.java:181) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.process(OperationThread.java:172) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.process(OperationThread.java:140) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.run(OperationThread.java:123) ~[hazelcast-all-4.0.1.jar:4.0.1]
```

The cluster throws this exception and return the exception the client. 

```
com.hazelcast.client.impl.proxy.ClientScheduledExecutorProxy.scheduleAtFixedRate
    public <V> IScheduledFuture<V> scheduleAtFixedRate(@Nonnull Runnable command, long initialDelay, long period,
                                                       @Nonnull TimeUnit unit) {
        checkNotNull(command, "Command is null");
        checkNotNull(unit, "Unit is null");

        String name = extractNameOrGenerateOne(command);
        int partitionId = getTaskOrKeyPartitionId(command, name);
        Callable adapter = createScheduledRunnableAdapter(command);
        TaskDefinition definition = new TaskDefinition(TaskDefinition.Type.AT_FIXED_RATE, name, adapter,
                initialDelay, period, unit);

        return scheduleOnPartition(name, definition, partitionId);
    }
```

The cluster (Hazelcast client is not a member of the cluster) runs the job.

So we need to deploy the job (codes) to the classpath of all the members of the cluster.

Question: How does a microservice which acts as a Hazelcast client use Hazelcast to schedule its jobs?

In my opinion, we can use a Queue to implement it.

Hazelcast Scheduler adds a task to the queue, and the microservice server takes the task from the queue.

## DuplicateTaskException

Caused by: com.hazelcast.scheduledexecutor.DuplicateTaskException: There is already a task with the same name 'task1' in 'scheduler1'

```
Caused by: com.hazelcast.scheduledexecutor.DuplicateTaskException: There is already a task with the same name 'task1' in 'scheduler1'
	at com.hazelcast.scheduledexecutor.impl.ScheduledExecutorContainer.checkNotDuplicateTask(ScheduledExecutorContainer.java:394) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.scheduledexecutor.impl.ScheduledExecutorContainer.schedule(ScheduledExecutorContainer.java:112) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.scheduledexecutor.impl.operations.ScheduleTaskOperation.run(ScheduleTaskOperation.java:45) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationservice.Operation.call(Operation.java:184) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationservice.impl.OperationRunnerImpl.call(OperationRunnerImpl.java:228) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationservice.impl.OperationRunnerImpl.run(OperationRunnerImpl.java:217) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationservice.impl.OperationRunnerImpl.run(OperationRunnerImpl.java:433) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.process(OperationThread.java:166) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.process(OperationThread.java:136) ~[hazelcast-all-4.0.1.jar:4.0.1]
	at com.hazelcast.spi.impl.operationexecutor.impl.OperationThread.run(OperationThread.java:123) ~[hazelcast-all-4.0.1.jar:4.0.1]
```

IScheduledExecutorService: <V> Map<Member, List<IScheduledFuture<V>>> getAllScheduledFutures()

IScheduledFuture: boolean cancel(boolean mayInterruptIfRunning)

We can check the registered scheduler and cancel a scheduler with IScheduledFuture if necessary.

Questions: If we run two instances of the application, one will have DuplicateTaskException.

It seems it is a little complicated if we need to update the definition of the job in the cluster: interval settings, job codes, etc.
