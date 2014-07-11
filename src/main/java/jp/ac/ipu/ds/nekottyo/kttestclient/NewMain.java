/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.ipu.ds.nekottyo.kttestclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.TokyoTyrantTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**
 *
 * @author nekottyo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String hostname = "localhost";
        String port = "11211";
        int expiration_time = 0;

        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses(hostname + ":" + port));
        builder.setTranscoder(new TokyoTyrantTranscoder());
        MemcachedClient client = builder.build();
        ArrayList<String> keylist = new ArrayList<String>();

        try {
            // set data
            System.out.println("set data");
            for (Integer i = 0; i != 5; i++) {
                String value = "value" + i;
                client.set(i.toString(), expiration_time, value);
                keylist.add(i.toString());
            }

            KeyIterator ite;
            ite = client.getKeyIterator(AddrUtil.getOneAddress("localhost:11211"));
            System.err.println("pppppprinttttt ");
            System.out.println(ite.toString());
            System.out.println(Boolean.toString(ite.hasNext()));
            while(ite.hasNext()){
                System.out.println(ite.next());
            }


            //test exac
            ProcessBroker pb = new ProcessBroker("/usr/local/Cellar/kyoto-tycoon/0.9.56/bin/ktremotemgr list -port 1991".split(" "));
            pb.execute();

            List<String> list = pb.getList();

            Iterator listIte = list.iterator();
            System.out.println("-+-+-+");
            while(listIte.hasNext()){
                System.out.println(listIte.next().toString());
            }
                        System.out.println("-+-+-+");

            System.out.println("end of leadline");





            // get data
            // bulk get
            System.out.println("bulk get");
            Map<String, Object> valuemap = client.get(keylist);
            for (Integer i = 0; i != 5; i++) {
                System.out.println(valuemap.get(keylist.get(i)));
            }

            // single get
            System.out.println("single get");
            for (Integer i = 0; i != 5; i++) {
                String value = client.get(i.toString());
                System.out.println(value);
            }
            // delete data
            System.out.println("delete data from db");

            for (Integer i = 0; i != 5; i++) {
                client.delete(i.toString());
            }

        } catch (TimeoutException | MemcachedException e) {
            // memcached operation timeout
            e.printStackTrace();
        } catch (InterruptedException e) {
            // ignore
            //e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
