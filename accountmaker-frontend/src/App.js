import logo from './logo.svg';
import './App.css';
import { useFetchCustomerData } from './hooks/useFetchCustomerData';
import { useState } from 'react';



function App() {
  const { data, isFetching } = useFetchCustomerData(
    `http://localhost:8080/v1/customer`
  );
  const [value, setValue] = useState('');
  const [dropDownValue, setDropDownValue] = useState('');
  const handleChange = (e)=>{
    setValue(e.target.value);
  }
  const handleDropDownChange = (e)=>{
    console.log(e.target.value)
    setDropDownValue(e.target.value);
  }
  async function onSubmitHandler(e){
    e.preventDefault()
    try{
      const res = await fetch("http://localhost:8080/v1/account", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          initialCredit: value,
          customerId: dropDownValue
      }),
      });
      if (!res.ok) {
        throw Error("could not fetch the data for that resource");
      }
      const data = await res.json();
      alert(data.message);
      return data;
    }catch(err) {
      console.log("error", err);
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <form onSubmit={onSubmitHandler}>
        <h5>Select Customer</h5>
       <select onChange={handleDropDownChange}>
       {
            data?.response?.map((i,id)=>{
             return <option key={id} value={i.customerId}>{i.name}</option>
            })
        }
       </select>
       <h5>Add Initial Credit</h5>
       <input value={value} onChange={handleChange}/>
       <button type="submit">Save</button>
       </form>
      </header>
    </div>
  );
}

export default App;
