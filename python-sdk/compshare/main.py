# -*- coding: utf-8 -*-
from ucloud.core import exc
from ucloud.client import Client


def main():
    client = Client({
        "region": "cn-wlcb", # always cn-wlcb
        "public_key": "",  #  public_key and private_ket can be got from https://console.compshare.cn/uaccount/api_manage
        "private_key": "",
        "base_url": "https://api.compshare.cn" # always https://api.compshare.cn
    })

    try:
        create_resp = client.ucompshare().create_comp_share_instance({
            "Zone": "cn-wlcb-01", # always cn-wlcb-01
            "MachineType": "G", # always G
            "CompShareImageId": "compshareImage-165jmhx19ik7",  # ID of image you want to use
            "GPU": 1,   # GPU count
            "GpuType": "4090", # GPU Type, it can be 4090, 3080Ti or 3090
            "CPU": 16, # CPU count
            "Memory": 64*1024, # Memory size, 64 * 1024 means 64G
            "Disks": [
                {
                    "IsBoot": True,
                    "Size": 200, # System disk size, 200 means 200G
                    "Type": "CLOUD_SSD"
                }
            ]

        })
        instance_ids = create_resp.get("UHostIds")
        describe_resp = client.ucompshare().describe_comp_share_instance({
            "UHostIds": instance_ids,
        })
        print(describe_resp)
    except exc.UCloudException as e:
        print(e)


if __name__ == '__main__':
    main()
