/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.ipu.ds.nekottyo.kttestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.transcoders.TokyoTyrantTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**
 *
 * @author nekottyo
 */
public class KyotoTycoonIO {

    String host;
    String port;
    int expiration_time = 0;

    MemcachedClient client;

    public KyotoTycoonIO() {
        this("localhost", "11211");
    }

    public KyotoTycoonIO(String host, String port) {
        this.host = host;
        this.port = port;

        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses(host + ":" + port));
        builder.setTranscoder(new TokyoTyrantTranscoder());
        try {
            client = builder.build();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putData(String key, String value) {
        try {
            client.set(key, expiration_time, value);
        } catch (Exception e) {
        }
    }

    public String getData(String key) {
        String value = null;
        try {
            value = client.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public void deleteData(String key) {
        try {
            client.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData(List<String> key) {
        Iterator ite = key.iterator();
        while (ite.hasNext()) {
            deleteData(ite.next().toString());
        }
    }

    public void deleteAllData() {
        List list = getKeylist();
        deleteData(list);
    }

    public List<String> getKeylist() {
        List list = null;
        try {
            ProcessBuilder pb = new ProcessBuilder("/usr/local/Cellar/kyoto-tycoon/0.9.56/bin/ktremotemgr",
                    "list", "-port", "1991");
            Process p = pb.start();
            p.waitFor();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                list = new ArrayList();
                String line;
                for (line = br.readLine(); line != null; line = br.readLine()) {
                    list.add(line.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setExpiration(int i) {
        this.expiration_time = i;
    }
}
