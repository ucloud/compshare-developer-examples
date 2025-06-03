package main

import (
	"fmt"

	"github.com/ucloud/ucloud-sdk-go/services/ucompshare"
	"github.com/ucloud/ucloud-sdk-go/ucloud"
	"github.com/ucloud/ucloud-sdk-go/ucloud/auth"
)

func main() {
	cfg := ucloud.NewConfig()
	cfg.Region = "cn-wlcb"                   // Region is always cn-wlcb for CompShare
	cfg.Zone = "cn-wlcb-01"                  // Zone is always cn-wlcb-01 for CompShare
	cfg.BaseUrl = "https://api.compshare.cn" // BaseUrl is always https://api.compshare.cn for CompShare

	// replace the public/private key by your own
	credential := auth.NewCredential()
	// Following keys can be got from https://console.compshare.cn/uaccount/api_manage
	credential.PublicKey = "my_public_key"   // replace with your own public key
	credential.PrivateKey = "my_private_key" // replace with your own private key
	client := ucompshare.NewClient(&cfg, &credential)

	createReq := client.NewCreateCompShareInstanceRequest()
	createReq.MachineType = ucloud.String("G")                                // MachineType is always G for CompShare
	createReq.CompShareImageId = ucloud.String("compshareImage-165jmhx19ik7") // you can replace the image with an available id
	createReq.GPU = ucloud.Int(1)                                             // specify the number of GPUs you want
	createReq.GpuType = ucloud.String("4090")                                 // specify the GPU type
	createReq.CPU = ucloud.Int(16)                                            // specify the number of CPUs you want
	createReq.Memory = ucloud.Int(64 * 1024)                                  // specify the memory size in MB
	createReq.Disks = []ucompshare.CreateCompShareInstanceParamDisks{}
	createReq.Disks = append(createReq.Disks, ucompshare.CreateCompShareInstanceParamDisks{
		Type:   ucloud.String("CLOUD_SSD"), // specify the disk type
		Size:   ucloud.Int(200),            // specify the disk size in GB
		IsBoot: ucloud.Bool(true),
	})
	// send request
	createResp, err := client.CreateCompShareInstance(createReq)
	if err != nil {
		fmt.Printf("something bad happened: %s\n", err)
	} else {
		fmt.Printf("resource id of the instance: %v\n", createResp.UHostIds)
	}
	describeReq := client.NewDescribeCompShareInstanceRequest()
	describeReq.UHostIds = createResp.UHostIds // use the resource id returned by the create request
	describeResp, err := client.DescribeCompShareInstance(describeReq)
	if err != nil {
		fmt.Printf("something bad happened: %s\n", err)
	} else {
		fmt.Printf("created instance: %v\n", describeResp.UHostSet)
	}
}
