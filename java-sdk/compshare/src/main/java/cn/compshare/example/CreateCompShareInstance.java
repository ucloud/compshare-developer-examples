package cn.compshare.example;

import cn.ucloud.common.config.Config;
import cn.ucloud.common.credential.Credential;
import cn.ucloud.common.exception.UCloudException;
import cn.ucloud.ucompshare.client.UCompShareClient;
import cn.ucloud.ucompshare.models.*;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class CreateCompShareInstance {
    public static void main(String[] args) throws UCloudException {
        // Following keys can be got from https://console.compshare.cn/uaccount/api_manage
        String publicKey = "";
        String privateKey = "";
        Config config = new Config();
        config.setRegion("cn-wlcb");  // always cn-wlcb
        config.setBaseUrl("https://api.compshare.cn"); // always https://api.compshare.cn
        Credential credential = new Credential(publicKey, privateKey);
        UCompShareClient client = new UCompShareClient(config, credential);
        // create a instance
        CreateCompShareInstanceRequest createCompShareInstanceRequest = new CreateCompShareInstanceRequest();
        createCompShareInstanceRequest.setZone("cn-wlcb-01"); // always cn-wlcb-01
        createCompShareInstanceRequest.setMachineType("G");
        createCompShareInstanceRequest.setCompShareImageId("compshareImage-165jmhx19ik7"); // ID of image you want to use
        createCompShareInstanceRequest.setGPU(1);  // GPU count
        createCompShareInstanceRequest.setGpuType("4090"); // GPU Type, it can be 4090, 3080Ti or 3090
        createCompShareInstanceRequest.setCPU(16); // CPU count
        createCompShareInstanceRequest.setMemory(64*1024); // Memory size, 64 * 1024 means 64G
        List<CreateCompShareInstanceRequest.Disks> disks = new ArrayList<>();
        CreateCompShareInstanceRequest.Disks bootDisk = new CreateCompShareInstanceRequest.Disks();
        bootDisk.setIsBoot(true);
        bootDisk.setSize(200); // System disk size, 200 means 200G
        bootDisk.setType("CLOUD_SSD");
        disks.add(bootDisk);
        createCompShareInstanceRequest.setDisks(disks);
        CreateCompShareInstanceResponse resp = client.createCompShareInstance(createCompShareInstanceRequest);
        List<String> ucloudIds = resp.getUHostIds();
        // query the information of created instances
        DescribeCompShareInstanceRequest describeCompShareInstanceRequest = new DescribeCompShareInstanceRequest();
        describeCompShareInstanceRequest.setUHostIds(ucloudIds);
        DescribeCompShareInstanceResponse describeCompShareInstanceResponse = client.describeCompShareInstance(describeCompShareInstanceRequest);
        Gson gson = new Gson();
        String json = gson.toJson(describeCompShareInstanceResponse);
        System.out.println(json);
    }
}