This directory contains mapping files that map your UI package to custom
written @Composable functions.

### Mapping file name
The name of the file should match the name of your UI package.

### Mapping file contents

- **target** : (Required) The name of your custom composable function. By
  default this is the name of the function created by generated code.

```
"target" : "CustomComposableName"
```

- **package** : (Required) Name of the package your custom composable sits in.
  By default this is the package of the function created by generated code.

```
"package" : "com.example.podcastapp.ui.components"
```

- **generateImplementation** : (Optional) true or false. If true, an
  implementation of this UI package will still be generated in the generated
  code file. If false, the implementation will not be generated. By default,
  this is true.

```
"generateImplementation" : true
```

- **generatePreviews** : (Optional) true or false. If true, a preview of the
  mapped custom component will be generated in the generated code file. If
  false, no preview will be generated. By default, this is true.

```
"generatePreviews" : true
```

- **fieldMappings** : If your UI package has parameters that do not match the
  custom composable's parameters in name, you will need to provide
  parameter mappings.
- **type** : The type of mapping to apply.
- **parameter** : maps a UI package field to a code parameter.
- **lambda** : maps a UI package field to a content lamda or modifier.
- **source** : the name of the parameter in the UI package.
- **target** : the name of the parameter in the target code component.

```
"fieldMappings": [
{
    "type": "parameter",
    "source": "content",
    "target": "contentText"
},
{
    "type": "lambda",
    "source": "frameContents",
    "target": "innerContents"
}
]
```
