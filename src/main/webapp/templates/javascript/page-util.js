const sendFormWithParameter = (parameterObject, method, action) => {
    let form = document.createElement("form");
    form.action = action;
    form.method = method;

    for (let parameterKey in parameterObject){
      const parameterValue = parameterObject[parameterKey]
      let input = document.createElement("input");
      input.setAttribute("type", "hidden");
      input.setAttribute('name', parameterKey);
      input.setAttribute("value", parameterValue);
      form.appendChild(input);
    }

    document.body.appendChild(form);
    form.submit();
  }

  const linkTo = (path, parameterObject) => {
    if (parameterObject === undefined) {
      window.location.href= `${path}`
      return;
    }
    let parameters = ""
    for (let parameterKey in parameterObject){
      const parameterValue = parameterObject[parameterKey]
      parameters += `${parameterKey}=${parameterValue}&`
    }
    if (parameters.endsWith('&')){
      parameters = parameters.slice(0, -1)
    }
    window.location.href = `${path}?${parameters}`
  }
