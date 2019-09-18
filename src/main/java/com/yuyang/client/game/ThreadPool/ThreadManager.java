package com.yuyang.client.game.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
	
	private ThreadManager(){}
	
	private static ThreadManager instance = new ThreadManager();
	
	public static ThreadManager getInstance(){
		return instance;
	}
	
	//有界队列，拒绝策略没有写
	public ExecutorService getPool(){
		//System.out.println(Runtime.getRuntime().availableProcessors());
        BlockingQueue<Runnable> queue = 
                new ArrayBlockingQueue<Runnable>(10); //有界队列
        ExecutorService executor  = new ThreadPoolExecutor(
                    5,      //core
                    10,     //max
                    2L,   //2秒
                    TimeUnit.SECONDS,
                    queue);
          return executor;
	
	}
	
	/****参数例子*****/
	/***
	public void ThreadPooIExecutor(
		    int corePoolSize, //corePoolSize：核心线程池的大小，当线程池中的线程数量达到corePoolSize之后，就把任务放到 缓存队列当中
			                  //线程池被创建之后，其实里面是没有线程的。
				              //（当然，调用prestartAllCoreThreads()或者prestartCoreThread()方法会预创建线程，而不用等着任务的到来）。
				              //当有任务进来的时候，才会创建线程。当线程池中的线程数量达到corePoolSize之后，就把任务放到 缓存队列当中。（就是 workQueue）。
		    
		    int maximumPoolSize, //最大线程数量是多少。它标志着这个线程池的最大线程数量。如果没有最大数量，当创建的线程数量达到了 某个极限值，到最后内存肯定就爆掉了。
		    
		    long keepAIiveTime ,  //当线程没有任务时，最多保持的时间，超过这个时间就被终止了。
		    					  //默认情况下，只有 线程池中线程数量 大于 corePoolSize时，keepAliveTime值才会起作用。
		    					  //也就说说，只有在线程池线程数量超出corePoolSize了。我们才会把超时的空闲线程给停止掉。
		    					  //否则就保持线程池中有 corePoolSize 个线程就可以了

		    TimeUnit unit,       //参数keepAliveTime的时间单位。
		    
		    BlockingQueue<Runnable> workQueue,  //用来存储待执行任务的队列，不同的线程池它的队列实现方式不同（因为这关系到排队策略的问题）比如有以下几种:
		    									//ArrayBlockingQueue：基于数组的队列，创建时需要指定大小。
		    									//LinkedBlockingQueue：基于链表的队列，如果没有指定大小，则默认值是 Integer.MAX_VALUE。（newFixedThreadPool和newSingleThreadExecutor使用的就是这种队列）。
		    									//SynchronousQueue：这种队列比较特殊，因为不排队就直接创建新线程把任务提交了。（newCachedThreadPool使用的就是这种队列）。

		    ThreadFactory threadFactory,		//线程工厂，用来创建线程。
		    RejectedExecutionHandler handler)   //拒绝执行任务时的策略，一般来讲有以下四种策略，
		    									//（1） ThreadPoolExecutor.AbortPolicy 丢弃任务，并抛出 RejectedExecutionException 异常。
		    									//（2） ThreadPoolExecutor.CallerRunsPolicy：该任务被线程池拒绝，由调用 execute方法的线程执行该任务。
		    									//（3） ThreadPoolExecutor.DiscardOldestPolicy ： 抛弃队列最前面的任务，然后重新尝试执行任务。
		    									//（4） ThreadPoolExecutor.DiscardPolicy，丢弃任务，不过也不抛出异常。

				{
			
				}
	 	****/
}
