```shell
Tests: running
WARNING: A command line option has enabled the Security Manager
WARNING: The Security Manager is deprecated and will be removed in a future release
Testing Count
    expression.TripleExpression
        Basic tests
Exception in thread "main" java.lang.AssertionError: base.toMiniString:
     original `-(0)`,
     expected `- 0`,
       actual `-0`
        at base.Asserts.error(Asserts.java:75)
        at base.Asserts.assertTrue(Asserts.java:41)
        at expression.parser.ParserTestSet.assertEquals(ParserTestSet.java:145)
        at expression.parser.ParserTestSet.lambda$checkToString$23(ParserTestSet.java:137)
        at base.TestCounter.lambda$test$0(TestCounter.java:58)
        at base.TestCounter.lambda$testV$2(TestCounter.java:71)
        at base.Log.silentScope(Log.java:40)
        at base.TestCounter.testV(TestCounter.java:70)
        at base.TestCounter.test(TestCounter.java:57)
        at expression.parser.ParserTestSet.checkToString(ParserTestSet.java:134)
        at expression.parser.ParserTestSet.test(ParserTestSet.java:99)
        at expression.common.TestGenerator.test(TestGenerator.java:51)
        at expression.common.TestGenerator.lambda$testBasic$27(TestGenerator.java:143)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
        at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        at java.base/java.util.stream.ReferencePipeline.forEachOrdered(ReferencePipeline.java:601)
        at expression.common.TestGenerator.lambda$testBasic$28(TestGenerator.java:141)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at expression.common.TestGenerator.testBasic(TestGenerator.java:139)
        at expression.parser.ParserTestSet.lambda$test$16(ParserTestSet.java:82)
        at base.Log.lambda$action$0(Log.java:18)
        at base.Log.silentScope(Log.java:40)
        at base.Log.scope(Log.java:31)
        at base.Log.scope(Log.java:24)
        at expression.parser.ParserTestSet.test(ParserTestSet.java:82)
        at expression.parser.ParserTester.test(ParserTester.java:54)
        at expression.parser.ParserTester.lambda$test$6(ParserTester.java:49)
        at base.Log.lambda$action$0(Log.java:18)
        at base.Log.silentScope(Log.java:40)
        at base.Log.scope(Log.java:31)
        at base.Log.scope(Log.java:24)
        at expression.parser.ParserTester.test(ParserTester.java:49)
        at base.Selector.lambda$composite$4(Selector.java:84)
        at base.Selector$Composite.lambda$v$0(Selector.java:134)
        at base.Selector.lambda$test$2(Selector.java:79)
        at base.Log.lambda$action$0(Log.java:18)
        at base.Log.silentScope(Log.java:40)
        at base.Log.scope(Log.java:31)
        at base.Log.scope(Log.java:24)
        at base.Selector.lambda$test$3(Selector.java:79)
        at java.base/java.lang.Iterable.forEach(Iterable.java:75)
        at base.Selector.test(Selector.java:79)
        at base.Selector.main(Selector.java:51)
        at expression.parser.ParserTest.main(ParserTest.java:31)
ERROR: Tests: failed
```