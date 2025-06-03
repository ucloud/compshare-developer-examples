# CompShare Developer Examples

A collection of SDK examples demonstrating how to use CompShare cloud computing APIs across multiple programming languages.

## Overview

CompShare is a cloud computing platform that provides GPU-accelerated computing instances. This repository contains example code and tutorials for integrating with CompShare APIs using different programming languages and SDKs.

## Supported Languages

| Language | SDK Directory | Status |
|----------|---------------|--------|
| Python   | [`python-sdk/`](./python-sdk/) | ✅ Ready |
| Go       | [`golang-sdk/`](./golang-sdk/) | ✅ Ready |
| Java     | [`java-sdk/`](./java-sdk/) | ✅ Ready |

## Features

These examples demonstrate how to:

- 🚀 Create CompShare GPU instances
- 🔧 Configure instance specifications (CPU, Memory, GPU)
- 📊 Query instance status and details
- 🔑 Authenticate using API keys

## Quick Start

### Prerequisites

Before running any examples, you'll need:

1. **CompShare Account**: Sign up at [CompShare Console](https://console.compshare.cn)
2. **API Credentials**: Get your `public_key` and `private_key` from [API Management](https://console.compshare.cn/uaccount/api_manage)
3. **Programming Environment**: Choose your preferred language and set up the development environment

### API Configuration

All examples require your CompShare API credentials:
- `public_key` and `private_key` from [API Management](https://console.compshare.cn/uaccount/api_manage)
- Region: `cn-wlcb` (standard)
- Zone: `cn-wlcb-01` (standard)
- Base URL: `https://api.compshare.cn` (standard)

## Documentation

- 📚 [CompShare API Documentation](https://www.compshare.cn/docs)
- 🔑 [API Key Management](https://console.compshare.cn/uaccount/api_manage)

## Support

If you encounter issues:

1. Check the language-specific README in each SDK directory
2. Verify your API credentials are correct
3. Ensure you're using the correct region (`cn-wlcb`) and zone (`cn-wlcb-01`)
4. Contact CompShare support through their console

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests to improve these examples.

