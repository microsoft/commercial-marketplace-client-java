
# Readme

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.opensource.microsoft.com.

When you submit a pull request, a CLA bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., status check, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.


# Azure Commericial Marketplace SaaS Client SDK for Java

This repository is for active development of the Azure Commericial Marketplace SaaS Client SDK for Java. For consumers of the SDK we recommend visiting our versioned [developer docs](./sdk/README.md).

## Getting started

For samples of how to use the methods, look to the [test code](./sdk/src/test/java/com/azure/marketplace/tests/).

You can run the tests from the command line by executing a build from the root of the repository: 
```bash
. ./variables.conf
mvn test --file sdk/pom.xml
```

You will need to have the following setup first:

1. You must have access to the [Partner Center Commercial Marketplace page.](https://partner.microsoft.com/dashboard/commercial-marketplace/). You can setup a Partner account by visiting the [partner page](https://partner.microsoft.com/) and selecting "Become a partner". Once you do that, enroll in the [Commercial Marketplace](https://docs.microsoft.com/azure/marketplace/partner-center-portal/create-account).  
1. You must have at least one SaaS offer published to the Preview stage. Instructions [here.](https://docs.microsoft.com/azure/marketplace/partner-center-portal/create-new-saas-offer)
1. You must have at least one subscription to the SaaS offer.

The tests assume the presence of the following environment variables. These variables allow the tests to login on your behalf.

- AAD_TENANT_ID: Same ID as is used on the Technical Configuration page for your SaaS offer.
- AAD_APP_CLIENT_ID: Same ID as is used on the Technical Configuration page for your SaaS offer.
- AAD_APP_CLIENT_SECRET: A secret associated with the AAD_APP_CLIENT_ID used on the Technical Configuration page for your SaaS offer.
- AAD_APP_CERT: A base64-encoded version of a certificate which also contains a private key. This certificate is used to authenticate the AAD_APP_CLIENT_ID. You can do the conversion in a bash shell with the command line with "base64 &lt;certificate.pfx&gt; -w 0". Websites also exist, such as [Base64.Guru](https://base64.guru/converter/encode/file).
- AAD_APP_CERT_SECRET: Password for the certificate.

You can make sure these are set by using the template [variables.conf.template](./variables.conf.template) and then copying that file to variables.conf. Once copied, fill in the proper values.

To create the certificate using OpenSSL, these lines work great:

```bash
openssl req -x509 -nodes -days 3650 -newkey rsa:4096 -keyout privateKey.key -out certificate.pem
openssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.pem
```

### Prerequisites

Java 11 or later is required to use this library. Prefer to use [Azul Java SDK](https://www.azul.com/downloads/zulu-community/?package=jdk)

### Client

Microsoft release Java packages follow the [Azure SDK Design Guidelines for Java](https://azure.github.io/azure-sdk/java/guidelines/) and share a number of core features such as HTTP retries, logging, transport protocols, authentication protocols, etc., so that once you learn how to use these features in one client library, you will know how to use them in other client libraries. You can learn about these shared features [here](https://github.com/Azure/azure-sdk-for-java/blob/master/sdk/core/azure-core/README.md).

> NOTE: If you need to ensure your code is ready for production use one of the stable, non-preview libraries.

## Need help?

- For reference documentation visit the [Azure Marketplace SDK for Java documentation](./sdk/README.md).
- For tutorials, samples, quick starts and other documentation, review the [test code](./sdk/src/test/java/com/microsoft/azure/marketplace/tests).
- File an issue via [Github Issues](https://github.com/Azure/commercial-marketplace-saas-sdk-client-java/issues/new/choose).

## Navigating the repository

### Master branch

The master branch has the most recent code with new features and bug fixes. It does **not** represent latest released **GA** SDK.

### Release branches (Release tagging)

For each package we release there will be a unique git tag created that contains the name and the version of the package to mark the commit of the code that produced the package. This tag will be used for servicing via hotfix branches as well as debugging the code for a particular preview or stable release version.
Format of the release tags are `<package-name>_<package-version>`. For more information please see [our branching strategy](https://github.com/Azure/azure-sdk/blob/master/docs/policies/repobranching.md#release-tagging).

## Contributing

For details on contributing to this repository, see the [contributing guide](CONTRIBUTING.md).

This project welcomes contributions and suggestions. Most contributions require you to agree to a Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us the rights to use your contribution. For details, view [Microsoft's CLA](https://cla.microsoft.com).

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions provided by the bot. You will only need to do this once across all repositories using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/). For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

### Additional Helpful Links for Contributors

Many people all over the world have helped make this project better.  You'll want to check out:

- [What are some good first issues for new contributors to the repo?](https://github.com/azure/azure-sdk-for-java/issues?q=is%3Aopen+is%3Aissue+label%3A%22up+for+grabs%22)
- [How to build and test your change](CONTRIBUTING.md#developer-guide)
- [How you can make a change happen!](CONTRIBUTING.md#pull-requests)
- Frequently Asked Questions (FAQ) and Conceptual Topics in the detailed [Azure SDK for Java wiki](https://github.com/azure/azure-sdk-for-java/wiki).

### Reporting security issues and security bugs

Security issues and bugs should be reported privately, via email, to the Microsoft Security Response Center (MSRC) <secure@microsoft.com>. You should receive a response within 24 hours. If for some reason you do not, please follow up via email to ensure we received your original message. Further information, including the MSRC PGP key, can be found in the [Security TechCenter](https://www.microsoft.com/msrc/faqs-report-an-issue).

### License

Azure Marketplace SDK for Java is licensed under the [MIT](LICENSE.txt) license.

<!-- Links -->
[java_guidelines](https://azure.github.io/azure-sdk/java_introduction.html)

![Impressions](https://azure-sdk-impressions.azurewebsites.net/api/impressions/commercial-marketplace-saas-sdk-client-java%2FREADME.png)