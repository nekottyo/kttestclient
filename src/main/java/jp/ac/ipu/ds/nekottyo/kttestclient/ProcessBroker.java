/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.ds.nekottyo.kttestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessBroker {
    private List<String> command;
    private StringBuilder stdout;
    private StringBuilder stderr;
    private List list;

    public ProcessBroker(String... command) {
        this.stderr = new StringBuilder();
        this.command = new ArrayList<>();
        this.stdout = new StringBuilder();
        this.list = new ArrayList();
        this.command.addAll(Arrays.asList(command));
    }

    public int execute() throws IOException, InterruptedException {
        Process process = new ProcessBuilder(command).start();
        new StreamReaderThread(process.getInputStream(), stdout).start();
        new StreamReaderThread(process.getErrorStream(), stderr).start();
        return process.waitFor();
    }

    public String getStdout() {
        return stdout.toString();
    }

    public String getStderr() {
        return stderr.toString();
    }

    public List getList(){
        return list;
    }

    class StreamReaderThread extends Thread {
        InputStream inputStream;
        StringBuilder output;

        StreamReaderThread(final InputStream inputStream, StringBuilder output) {
            this.inputStream = inputStream;
            this.output = output;
        }

        @Override public void start() {
            try {
                readStream(inputStream, output);
            } catch (IOException e) {
                output.append(e.getMessage());
            }
        }

        private void readStream(InputStream inputStream, StringBuilder sb)
                throws IOException {
            try {
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br.readLine()) != null){
                    sb.append(line + "\n");
                    list.add(line);
                }
            } finally {
                //br.close();
            }
        }
    }
}
