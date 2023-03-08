import {useEffect, useState} from "react";
import {useAtom} from "jotai";
import {getData} from "../utils/apiManagement";
import {store} from "../stateManagement/Store";
import TodoElement from "../components/ToDoElement";

export default function Completed() {
    const REACT_APP_COMPLETED_TASKS = process.env.REACT_APP_COMPLETED_TASKS;
    const [completedTasks, setCompletedTasks] = useState([]);
    const [added] = useAtom(store);

    useEffect(() => {
        getData(REACT_APP_COMPLETED_TASKS).then((response) =>
            setCompletedTasks(response)
        );
    }, [added]);

    return (
        <div className="tasks-box">
            {completedTasks.map((task) => (
                <TodoElement key={task.taskName + task.id} task={task}/>
            ))}
        </div>
    );
}
