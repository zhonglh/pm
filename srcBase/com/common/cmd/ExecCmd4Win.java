package com.common.cmd;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;



public class ExecCmd4Win {

    public static Logger logger = Logger.getLogger (ExecCmd4Win.class);

    /**
     * @param cmd
     *            传入 调用脚本的命令
     * @return 返回 流中的数据
     * @throws VMException
     */
    public static String Runtime(String[] cmd){
    	    	
        final StringBuffer errBuffer = new StringBuffer ();
        // 正确流
        InputStreamReader getInput = null;
        try {
            Runtime runtime = Runtime.getRuntime ();	
            Process process = runtime.exec (cmd[0]);
            if (process != null) {
                /** 创建错误流 */
                final InputStreamReader errInput = new InputStreamReader (process.getErrorStream ());

                ErrorStreamThread errThread = new ErrorStreamThread () {

                    public void run(){
                        BufferedReader errReader = new BufferedReader (errInput);
                        try {
                            String line = null;
                            while ((line = errReader.readLine ()) != null) {
                                errBuffer.append (line);
                            }
                        } catch (IOException e) {
                            logger.error (e.toString (), e);
                            this.setException (new RuntimeException (e.getMessage ()));
                        } finally {
                            // 关闭错误流
                            if (errInput != null) {
                                try {
                                    errInput.close ();
                                } catch (IOException e) {
                                    logger.error ("errInput close", e);
                                    this.setException (new RuntimeException (e.getMessage ()));
                                }
                            }
                        }
                    }
                };
                errThread.start ();

                getInput = new InputStreamReader (process.getInputStream ());
                BufferedReader getInputBUffer = new BufferedReader (getInput);
                StringBuffer stdinBuffer = new StringBuffer ();
                String line;
                while ((line = getInputBUffer.readLine ()) != null) {
                    if (line.length () > 0) {
                        stdinBuffer.append (line);
                        stdinBuffer.append ("\r\n");
                    }
                }

                try {
                    // 正常流读取结束后，合并异常流的子线程
                    errThread.join ();
                } catch (InterruptedException e) {
                    logger.error ("join the child thread appeared exception:", e);
                    throw new RuntimeException (e.getMessage ());
                }

                if (errThread.hasVMException ()) { 
                	// 子线程中发生的异常
                    throw errThread.getException ();
                }

                if (errBuffer != null && !"".equals(errBuffer.toString ())) {
                    //错误流信息
                    logger.debug ("errBuffer-----------" + errBuffer.toString ());
                    throw new RuntimeException (errBuffer.toString ());
                }

                if (stdinBuffer != null && stdinBuffer.toString ().length () > 0) { 
                	logger.debug("cmd : "+cmd+"   ; resutlt===\n"+stdinBuffer.toString ());
                	return stdinBuffer.toString (); 
                }
            }
        } catch (IOException e) {
        	logger.debug("cmd : "+cmd);
            logger.error (e.toString (), e);
            throw new RuntimeException (e.getMessage (),e);
        } finally {
            // 关闭正确流
            if (getInput != null) {
                try {
                    getInput.close ();
                } catch (IOException e) {
                    logger.error ("errInput close", e);
                }
            }

        }

    	logger.debug("cmd : "+cmd+"   ; resutlt= ");
        return "";
    }

    /**
     * 该方法只验证错误流，如果有操作系统错误，就抛出
     * 
     * @param cmd
     * @throws VMException
     */
    public static void validateErrorStream(String[] cmd){
        String data = Runtime (cmd);
               
    }

}
