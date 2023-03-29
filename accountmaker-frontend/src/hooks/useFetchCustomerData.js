import { useState, useEffect } from 'react';

export const useFetchCustomerData = (url, auth) => {
  const [data, setData] = useState();
  const [isFetching, setIsFetching] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const abortCont = new AbortController();
    setTimeout(() => {
      fetch(url, 
        {
          headers: { "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "http://localhost:8080",
          'Access-Control-Allow-Credentials': 'true',
            Authorization: `Bearer ${auth}`
          }, signal: abortCont.signal })
        .then((res) => {
          if (!res.ok) {
            throw Error('could not fetch')
}
          return res.json();
        })
        .then((data) => {
          setData(data);
          setIsFetching(false);
          setError(null);
        })
        .catch((err) => {
          if (err.name === 'AbortError') {
            console.log('fetch aborted');
          } else {
            setIsFetching(false);
            setError(err.message);
          }
        });
    }, 1000);

    return () => abortCont.abort();
  }, [url]);

  return { data, isFetching, error };
};
