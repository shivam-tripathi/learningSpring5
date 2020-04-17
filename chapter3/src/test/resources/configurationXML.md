# Configuration using XML

### Declaring beans
Use the <bean /> tag. Attributes:
- **name**: Qualified name of the bean. Defaults to class name.
- **class**: Fully qualified class of the bean.
- **scope**: The lifetime of the bean component. It determines if `getBean()` would return the same instance for every invocation (singleton) or if spring would create a new instance on every call to `getBean()`. Defaults to `singleton`, can use `prototype`.
- **lazy-init**: When in the context's lifecycle, should the bean be created. Default is false, bean is created immediately and eagerly. If true it will be instantiated on request and not on startup.
- **autowire**: Controls autowiring for this bean. Spring doesn't autowire references by default. This setting allows programmers to fine tune autowiring behavior, can be set to 'no', `byName` and `byType` (foo will match component with name foo) or by reference type, and `constructor` which is like `byType` except for constructor arguments.
- **dependson**: Comma separated list of beans which must be initialized before this one. Spring in most cases is able to determine dependencies, but sometimes in cases of static references etc default dependecy mechanism may fail.

Other attributes include `init-method`, `destroy-method`, `factory-method`, `factory-bean`.

### Wiring components using <property>
Components can be autowired using `<property>` tag.
- **name**: Name of the property tag
- **value**: Value of the reference, which can be easily coerced into type. Cannot coexist with ref.
- **ref**: Reference to another named Spring bean, by name. Cannot coexist with value.

### Wiring component using <contructor-arg>
Contructor injection using `<contructor-arg>` tag.
- **type**: Type of the argument, if it cannot be derived from the value.Must match the type hierarchy.
- **value**: Value of the argument, which must be easily coerced into type. Cannot coexist with ref.
- **ref**: References to another named spring bean by name. Cannot coexist with value.
- **name**: Name of the argument, in the constructor's code. Cannot coexist with index.
- **index**: Index of the argument being set. Cannot coexist with index.
