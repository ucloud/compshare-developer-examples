const { Client } = require("@ucloud-sdks/ucloud-sdk-js");

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function main() {
    // Build client
    const client = new Client({
        config: {
            region: 'cn-wlcb',
            baseUrl: 'https://api.compshare.cn',
            maxRetries: 3
        },
        credential: {
            publicKey: process.env.UCLOUD_PUBLIC_KEY || '',
            privateKey: process.env.UCLOUD_PRIVATE_KEY || '',
        }
    });

    try {
        // Create CompShare Instance
        const createResp = await client.invoke({
            Action: "CreateCompShareInstance",
            Region: 'cn-wlcb',
            Zone: "cn-wlcb-01",
            MachineType: "G",
            CompShareImageId: "compshareImage-165jmhx19ik7",
            GPU: 1,
            GpuType: "4090",
            CPU: 16,
            Memory: 64 * 1024,
            Disks: [
                {
                    IsBoot: true,
                    Size: 200,
                    Type: "CLOUD_SSD"
                }
            ]
        });

        const instanceIds = createResp.UHostIds;

        // Describe CompShare Instance
        const describeResp = await client.invoke({
            Action: "DescribeCompShareInstance",
            UHostIds: instanceIds
        });

        console.log(describeResp);

    } catch (error) {
        if (error.typ === 'EXC_TYPE_RET_CODE') {
            console.error('Error Code:', error.retCode);
            console.error('Request ID:', error.requestId);
        }
        console.error('Error Message:', error.message);
    }
}

// Run the main function
main().catch(error => {
    console.error('Unhandled error:', error);
}); 