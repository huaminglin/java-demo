# Jenkins Plugin

## Jenkins HelloWorld Plugin

mvn -U archetype:generate -Dfilter="io.jenkins.archetypes:"

```
4: remote -> io.jenkins.archetypes:hello-world-plugin (Skeleton of a Jenkins plugin with a POM and an example build step.)

Choose io.jenkins.archetypes:hello-world-plugin version: 6: 1.6

[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: hello-world-plugin:1.6
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: huaminglin.demo
[INFO] Parameter: artifactId, Value: jenkins-demo
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: huaminglin.demo
[INFO] Parameter: packageInPathFormat, Value: huaminglin/demo
[INFO] Parameter: package, Value: huaminglin.demo
[INFO] Parameter: groupId, Value: huaminglin.demo
[INFO] Parameter: artifactId, Value: jenkins-demo
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Project created from Archetype in dir: /home/myname/workspace/jenkins-demo
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  22:03 min
[INFO] Finished at: 2020-08-02T21:36:46+08:00
[INFO] ------------------------------------------------------------------------
```

## huaminglin.demo.HelloWorldBuilder.perform

```
"Executor #0 for slave1 : executing project1 #16@9219" daemon prio=5 tid=0xa0 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.HelloWorldBuilder.perform(HelloWorldBuilder.java:46)
	  at hudson.tasks.BuildStepCompatibilityLayer.perform(BuildStepCompatibilityLayer.java:78)
	  at hudson.tasks.BuildStepMonitor$1.perform(BuildStepMonitor.java:20)
	  at hudson.model.AbstractBuild$AbstractBuildExecution.perform(AbstractBuild.java:741)
	  at hudson.model.Build$BuildExecution.build(Build.java:206)
	  at hudson.model.Build$BuildExecution.doRun(Build.java:163)
	  at hudson.model.AbstractBuild$AbstractBuildExecution.run(AbstractBuild.java:504)
	  at hudson.model.Run.execute(Run.java:1880)
	  at hudson.model.FreeStyleBuild.run(FreeStyleBuild.java:43)
	  at hudson.model.ResourceController.execute(ResourceController.java:97)
	  at hudson.model.Executor.run(Executor.java:428)
```

## huaminglin.demo.HelloWorldDispatcher.canTake

```
HelloWorldDispatcher.canRun(): hudson.model.Queue$WaitingItem:hudson.model.FreeStyleProject@6f24859[project1]:17
HelloWorldDispatcher.canRun(): hudson.model.Queue$BuildableItem:hudson.model.FreeStyleProject@6f24859[project1]:17
HelloWorldDispatcher.canTake(): hudson.slaves.DumbSlave[slave1], hudson.model.Queue$BuildableItem:hudson.model.FreeStyleProject@6f24859[project1]:17
```

hudson.Extension:

Marks a field, a method, or a class for automatic discovery, so that Hudson can locate implementations of ExtensionPoints automatically.

In a simplest case, put this on your class, and Hudson will create an instance of it and register it to the appropriate ExtensionList.

```
"AtmostOneTaskExecutor[Periodic Jenkins queue maintenance] [#6]@9303" daemon prio=5 tid=0x7e nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.HelloWorldDispatcher.canRun(HelloWorldDispatcher.java:22)
	  at hudson.model.Queue.getCauseOfBlockageForItem(Queue.java:1209)
	  at hudson.model.Queue.maintain(Queue.java:1571)
	  at hudson.model.Queue$1.call(Queue.java:335)
	  at hudson.model.Queue$1.call(Queue.java:332)
	  at jenkins.util.AtmostOneTaskExecutor$1.call(AtmostOneTaskExecutor.java:108)
	  at jenkins.util.AtmostOneTaskExecutor$1.call(AtmostOneTaskExecutor.java:98)
	  at jenkins.security.ImpersonatingExecutorService$2.call(ImpersonatingExecutorService.java:71)
	  at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	  at hudson.remoting.AtmostOneThreadExecutor$Worker.run(AtmostOneThreadExecutor.java:111)
	  at java.lang.Thread.run(Thread.java:748)
```

```
"AtmostOneTaskExecutor[Periodic Jenkins queue maintenance] [#6]@9303" daemon prio=5 tid=0x7e nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at huaminglin.demo.HelloWorldDispatcher.canTake(HelloWorldDispatcher.java:16)
	  at hudson.model.Queue$JobOffer.getCauseOfBlockage(Queue.java:282)
	  at hudson.model.Queue.maintain(Queue.java:1633)
	  at hudson.model.Queue$1.call(Queue.java:335)
	  at hudson.model.Queue$1.call(Queue.java:332)
	  at jenkins.util.AtmostOneTaskExecutor$1.call(AtmostOneTaskExecutor.java:108)
	  at jenkins.util.AtmostOneTaskExecutor$1.call(AtmostOneTaskExecutor.java:98)
	  at jenkins.security.ImpersonatingExecutorService$2.call(ImpersonatingExecutorService.java:71)
	  at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	  at hudson.remoting.AtmostOneThreadExecutor$Worker.run(AtmostOneThreadExecutor.java:111)
	  at java.lang.Thread.run(Thread.java:748)
```

## "Build Now" Http request handle

```
"Handling POST /job/project1/build from 192.168.16.1 : Jetty (winstone)-100@9409" prio=5 tid=0x64 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at jenkins.util.AtmostOneTaskExecutor.maybeRun(AtmostOneTaskExecutor.java:97)
	  - locked <0x24c3> (a jenkins.util.AtmostOneTaskExecutor)
	  at jenkins.util.AtmostOneTaskExecutor.submit(AtmostOneTaskExecutor.java:86)
	  at hudson.model.Queue.scheduleMaintenance(Queue.java:1191)
	  at hudson.model.Queue.scheduleInternal(Queue.java:646)
	  at hudson.model.Queue.schedule2(Queue.java:601)
	  at hudson.model.Queue.schedule2(Queue.java:721)
	  at jenkins.model.ParameterizedJobMixIn.doBuild(ParameterizedJobMixIn.java:216)
	  at jenkins.model.ParameterizedJobMixIn$ParameterizedJob.doBuild(ParameterizedJobMixIn.java:407)
	  at java.lang.invoke.LambdaForm$DMH.1365042831.invokeInterface_L4_V(LambdaForm$DMH:-1)
	  at java.lang.invoke.LambdaForm$BMH.1013367628.reinvoke(LambdaForm$BMH:-1)
	  at java.lang.invoke.LambdaForm$MH.1869219059.invoker(LambdaForm$MH:-1)
	  at java.lang.invoke.LambdaForm$MH.295509005.invokeExact_MT(LambdaForm$MH:-1)
	  at java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:627)
	  at org.kohsuke.stapler.Function$MethodFunction.invoke(Function.java:396)
	  at org.kohsuke.stapler.Function$InstanceFunction.invoke(Function.java:408)
	  at org.kohsuke.stapler.Function.bindAndInvoke(Function.java:212)
	  at org.kohsuke.stapler.Function.bindAndInvokeAndServeResponse(Function.java:145)
	  at org.kohsuke.stapler.MetaClass$11.doDispatch(MetaClass.java:535)
	  at org.kohsuke.stapler.NameBasedDispatcher.dispatch(NameBasedDispatcher.java:58)
	  at org.kohsuke.stapler.Stapler.tryInvoke(Stapler.java:747)
	  at org.kohsuke.stapler.Stapler.invoke(Stapler.java:878)
	  at org.kohsuke.stapler.MetaClass$4.doDispatch(MetaClass.java:280)
	  at org.kohsuke.stapler.NameBasedDispatcher.dispatch(NameBasedDispatcher.java:58)
	  at org.kohsuke.stapler.Stapler.tryInvoke(Stapler.java:747)
	  at org.kohsuke.stapler.Stapler.invoke(Stapler.java:878)
	  at org.kohsuke.stapler.Stapler.invoke(Stapler.java:676)
	  at org.kohsuke.stapler.Stapler.service(Stapler.java:238)
	  at javax.servlet.http.HttpServlet.service(HttpServlet.java:790)
	  at org.eclipse.jetty.servlet.ServletHolder.handle(ServletHolder.java:755)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1617)
	  at hudson.util.PluginServletFilter$1.doFilter(PluginServletFilter.java:154)
	  at jenkins.security.ResourceDomainFilter.doFilter(ResourceDomainFilter.java:76)
	  at hudson.util.PluginServletFilter$1.doFilter(PluginServletFilter.java:151)
	  at jenkins.telemetry.impl.UserLanguages$AcceptLanguageFilter.doFilter(UserLanguages.java:128)
	  at hudson.util.PluginServletFilter$1.doFilter(PluginServletFilter.java:151)
	  at hudson.util.PluginServletFilter.doFilter(PluginServletFilter.java:157)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at hudson.security.csrf.CrumbFilter.doFilter(CrumbFilter.java:153)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:84)
	  at hudson.security.UnwrapSecurityExceptionFilter.doFilter(UnwrapSecurityExceptionFilter.java:51)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at jenkins.security.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at org.acegisecurity.providers.anonymous.AnonymousProcessingFilter.doFilter(AnonymousProcessingFilter.java:125)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at org.acegisecurity.ui.rememberme.RememberMeProcessingFilter.doFilter(RememberMeProcessingFilter.java:142)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at org.acegisecurity.ui.AbstractProcessingFilter.doFilter(AbstractProcessingFilter.java:271)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at jenkins.security.BasicHeaderProcessor.doFilter(BasicHeaderProcessor.java:93)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at org.acegisecurity.context.HttpSessionContextIntegrationFilter.doFilter(HttpSessionContextIntegrationFilter.java:249)
	  at hudson.security.HttpSessionContextIntegrationFilter2.doFilter(HttpSessionContextIntegrationFilter2.java:67)
	  at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	  at hudson.security.ChainedServletFilter.doFilter(ChainedServletFilter.java:90)
	  at hudson.security.HudsonFilter.doFilter(HudsonFilter.java:171)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at org.kohsuke.stapler.compression.CompressionFilter.doFilter(CompressionFilter.java:49)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at hudson.util.CharacterEncodingFilter.doFilter(CharacterEncodingFilter.java:82)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at org.kohsuke.stapler.DiagnosticThreadNameFilter.doFilter(DiagnosticThreadNameFilter.java:30)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at jenkins.security.SuspiciousRequestFilter.doFilter(SuspiciousRequestFilter.java:36)
	  at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1604)
	  at org.eclipse.jetty.servlet.ServletHandler.doHandle(ServletHandler.java:545)
	  at org.eclipse.jetty.server.handler.ScopedHandler.handle(ScopedHandler.java:143)
	  at org.eclipse.jetty.security.SecurityHandler.handle(SecurityHandler.java:566)
	  at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:127)
	  at org.eclipse.jetty.server.handler.ScopedHandler.nextHandle(ScopedHandler.java:235)
	  at org.eclipse.jetty.server.session.SessionHandler.doHandle(SessionHandler.java:1610)
	  at org.eclipse.jetty.server.handler.ScopedHandler.nextHandle(ScopedHandler.java:233)
	  at org.eclipse.jetty.server.handler.ContextHandler.doHandle(ContextHandler.java:1300)
	  at org.eclipse.jetty.server.handler.ScopedHandler.nextScope(ScopedHandler.java:188)
	  at org.eclipse.jetty.servlet.ServletHandler.doScope(ServletHandler.java:485)
	  at org.eclipse.jetty.server.session.SessionHandler.doScope(SessionHandler.java:1580)
	  at org.eclipse.jetty.server.handler.ScopedHandler.nextScope(ScopedHandler.java:186)
	  at org.eclipse.jetty.server.handler.ContextHandler.doScope(ContextHandler.java:1215)
	  at org.eclipse.jetty.server.handler.ScopedHandler.handle(ScopedHandler.java:141)
	  at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:127)
	  at org.eclipse.jetty.server.Server.handle(Server.java:500)
	  at org.eclipse.jetty.server.HttpChannel.lambda$handle$1(HttpChannel.java:383)
	  at org.eclipse.jetty.server.HttpChannel$$Lambda$143.1383990051.dispatch(Unknown Source:-1)
	  at org.eclipse.jetty.server.HttpChannel.dispatch(HttpChannel.java:547)
	  at org.eclipse.jetty.server.HttpChannel.handle(HttpChannel.java:375)
	  at org.eclipse.jetty.server.HttpConnection.onFillable(HttpConnection.java:273)
	  at org.eclipse.jetty.io.AbstractConnection$ReadCallback.succeeded(AbstractConnection.java:311)
	  at org.eclipse.jetty.io.FillInterest.fillable(FillInterest.java:103)
	  at org.eclipse.jetty.io.ChannelEndPoint$2.run(ChannelEndPoint.java:117)
	  at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.runTask(EatWhatYouKill.java:336)
	  at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.doProduce(EatWhatYouKill.java:313)
	  at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.tryProduce(EatWhatYouKill.java:171)
	  at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.run(EatWhatYouKill.java:129)
	  at org.eclipse.jetty.util.thread.ReservedThreadExecutor$ReservedThread.run(ReservedThreadExecutor.java:375)
	  at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:806)
	  at org.eclipse.jetty.util.thread.QueuedThreadPool$Runner.run(QueuedThreadPool.java:938)
	  at java.lang.Thread.run(Thread.java:748)
```

jenkins.model.ParameterizedJobMixIn.doBuild
    Queue.Item item = Jenkins.getInstance().getQueue().schedule2(asJob(), delay.getTimeInSeconds(), getBuildCause(asJob(), req)).getItem();

jenkins.model.Jenkins
    public Queue getQueue() { return queue; }

hudson.model.Queue.schedule2(hudson.model.Queue.Task, int, hudson.model.Action...)
    public @Nonnull ScheduleResult schedule2(Task p, int quietPeriod, Action... actions)


## hudson.model.Queue.maintainerThread

```
    private final Set<WaitingItem> waitingList = new TreeSet<WaitingItem>();
    private final ItemList<BlockedItem> blockedProjects = new ItemList<BlockedItem>();
    private final ItemList<BuildableItem> buildables = new ItemList<BuildableItem>();
    private final ItemList<BuildableItem> pendings = new ItemList<BuildableItem>();
    private transient volatile Snapshot snapshot = new Snapshot(waitingList, blockedProjects, buildables, pendings);

    private transient final AtmostOneTaskExecutor<Void> maintainerThread = new AtmostOneTaskExecutor<Void>(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            maintain();
            return null;
        }

        @Override
        public String toString() {
            return "Periodic Jenkins queue maintenance";
        }
    });


    /**
     * Queue maintenance.
     *
     * <p>
     * Move projects between {@link #waitingList}, {@link #blockedProjects}, {@link #buildables}, and {@link #pendings}
     * appropriately.
     *
     * <p>
     * Jenkins internally invokes this method by itself whenever there's a change that can affect
     * the scheduling (such as new node becoming online, # of executors change, a task completes execution, etc.),
     * and it also gets invoked periodically (see {@link Queue.MaintainTask}.)
     */
    public void maintain() {

...
            // The executors that are currently waiting for a job to run.
            Map<Executor, JobOffer> parked = new HashMap<Executor, JobOffer>();

                CauseOfBlockage causeOfBlockage = getCauseOfBlockageForItem(p); // canRun()

                    for (JobOffer j : parked.values()) {
                        CauseOfBlockage reason = j.getCauseOfBlockage(p); // canTake()
                        if (reason == null) {
                            LOGGER.log(Level.FINEST,
                                    "{0} is a potential candidate for task {1}",
                                    new Object[]{j, taskDisplayName});
                            candidates.add(j);
                        } else {
                            LOGGER.log(Level.FINEST, "{0} rejected {1}: {2}", new Object[] {j, taskDisplayName, reason});
                            reasons.add(reason);
                        }
                    }
}


    /**
     * Checks if the given item should be prevented from entering into the {@link #buildables} state
     * and instead stay in the {@link #blockedProjects} state.
     *
     * @return the reason of blockage if it exists null otherwise.
     */
    @CheckForNull
    private CauseOfBlockage getCauseOfBlockageForItem(Item i) {
        CauseOfBlockage causeOfBlockage = getCauseOfBlockageForTask(i.task);
        if (causeOfBlockage != null) {
            return causeOfBlockage;
        }

        for (QueueTaskDispatcher d : QueueTaskDispatcher.all()) {
            causeOfBlockage = d.canRun(i);
            if (causeOfBlockage != null)
                return causeOfBlockage;
        }

        if(!(i instanceof BuildableItem)) {
            // Make sure we don't queue two tasks of the same project to be built
            // unless that project allows concurrent builds. Once item is buildable it's ok.
            //
            // This check should never pass. And must be remove once we can completely rely on `getCauseOfBlockage`.
            // If `task.isConcurrentBuild` returns `false`,
            // it should also return non-null value for `task.getCauseOfBlockage` in case of on-going execution.
            // But both are public non-final methods, so, we need to keep backward compatibility here.
            // And check one more time across all `buildables` and `pendings` for O(N) each.
            if (!i.task.isConcurrentBuild() && (buildables.containsKey(i.task) || pendings.containsKey(i.task))) {
                return CauseOfBlockage.fromMessage(Messages._Queue_InProgress());
            }
        }

        return null;
    }


    public Future<?> scheduleMaintenance() {
        // LOGGER.info("Scheduling maintenance");
        return maintainerThread.submit();
    }
```

Note: hudson.model.Queue is the core of Jenkins.
