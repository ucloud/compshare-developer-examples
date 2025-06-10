<?php

use UCloud\Core\Client;
use UCloud\Core\Exception\UCloudException;
use UCloud\Core\Request\Request;

$client = new Client([
    "publicKey" => "public_key",
    "privateKey" => "private_key",
    "region" => "cn-wlcb",
    "baseUrl" => "https://api.compshare.cn"
]);

try {
    // Create CompShare Instance
    $createResp = $client->invoke(new Request([
        "Action" => "CreateCompShareInstance",
        "Zone" => "cn-wlcb-01",
        "MachineType" => "G",
        "CompShareImageId" => "compshareImage-165jmhx19ik7",
        "GPU" => 1,
        "GpuType" => "4090",
        "CPU" => 16,
        "Memory" => 64 * 1024,
        "Disks" => [
            [
                "IsBoot" => true,
                "Size" => 200,
                "Type" => "CLOUD_SSD"
            ]
        ]
    ]));

    $instanceIds = $createResp["UHostIds"];

    // Describe CompShare Instance
    $describeResp = $client->invoke(new Request([
        "Action" => "DescribeCompShareInstance",
        "UHostIds" => $instanceIds
    ]));

    print_r($describeResp);

} catch (UCloudException $e) {
    if ($e->getType() == UCloudException::EXC_TYPE_RET_CODE) {
        echo "Error Code: " . $e->getCode() . "\n";
        echo "Request ID: " . $e->getRequestId() . "\n";
    }
    echo "Error Message: " . $e->getMessage() . "\n";
} 