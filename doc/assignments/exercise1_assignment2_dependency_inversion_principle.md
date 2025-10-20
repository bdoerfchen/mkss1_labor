# Exercise dependency inversion principle
## Subtask 2.1
Instead of the UseCase depending directly on the DataProvider there now is an interface called UseCaseDataInterface which serves as a contract between the UseCase and DataProvider and is implemented by DataProvider. This way the data source is interchangeable without changing the UseCase.
![](../diagram/export/dependency_inversion_principle.svg)

## Subtask 2.2
To update the UseCase with new product data we introduce a new interface called DataReceiver which is used by DataProvider and implemented by UseCase. On initialization of DataProvider an instance of DataReceiver is given which then can be used to notify when the data is available.
![](../diagram/export/dependency_inversion_principle_2.svg)