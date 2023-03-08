import {useEffect, useState} from "react";
import {Outlet} from "react-router-dom";
import {useAtom} from "jotai";

import TodoElement from "../components/ToDoElement";
import Modal from "./Modal";
import {getData} from "../utils/apiManagement";
import {ORDER_CRITERIA, SORT_BY_DATE, store} from "../stateManagement/Store";

export default function HomePage() {
    const REACT_APP_CRUD_API = process.env.REACT_APP_CRUD_API;
    const REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_ASCENDING = process.env.REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_ASCENDING;
    const REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_DESCENDING = process.env.REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_DESCENDING;
    const [tasksByDate, setTasksByDate] = useAtom(SORT_BY_DATE);
    const [orderCriteria] = useAtom(ORDER_CRITERIA);
    const [added] = useAtom(store);
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        getData(REACT_APP_CRUD_API).then((response) => setTasks(response));
    }, []);

    useEffect(() => {
        getData(REACT_APP_CRUD_API).then((response) => setTasks(response));
    }, [added]);

    useEffect(() => {
        if (orderCriteria === "Ascending") {
            getData(REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_ASCENDING + false)
                .then((response) => setTasksByDate(response));
        } else if (orderCriteria === "Descending") {
            getData(REACT_APP_ALL_COMPLETED_TASKS_BY_DEADLINE_DESCENDING + false)
                .then((response) => setTasksByDate(response));
        }
        setTasks(tasksByDate);
    }, [orderCriteria]);

    return (
        <>
            <div className="container">
                <div className="tasks-box">
                    {tasks?.map((task) => (
                        <TodoElement key={task.id} task={task}/>
                    ))}
                </div>
            </div>
            <Modal/>
            <Outlet/>
        </>
    );
}
