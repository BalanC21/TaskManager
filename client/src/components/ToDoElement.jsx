import {useState} from "react";
import {deleteData, patchData} from "../utils/apiManagement";

import work from "../assets/business-16.png"
import home from "../assets/home-page-16.png"
import hobby from "../assets/music-16.png"


const TodoElement = ({task}) => {
    const [bool, setBool] = useState(true);

    function hideTask() {
        setBool(false);
    }

    const setTaskColor = (taskType) => `task-${taskType.toLowerCase()}`;
    const checkDeadline = () => {
        const currentDate = new Date();
        const deadline = new Date(task.deadLine);
        const diffInMs = deadline.getTime() - currentDate.getTime();
        const diffInDays = diffInMs / (24 * 60 * 60 * 1000);
        return diffInDays <= 1;
    }


    const alterTask = (id, callback) => {
        callback(`api/tasks/${id}`).then((r) => alert(r.response));
        hideTask();
    };

    function setImg() {
        let taskType = task.taskType
        if (taskType === "WORK") return work;
        if (taskType === "HOBBY") return hobby;
        if (taskType === "HOME") return home;
    }

    return (
        <>
            {bool ? (
                <div className={"task-box"}>
                    <div className={"card-title"}>
                        {!task.completed ? (
                            <input
                                onChange={() => alterTask(task.id, patchData)}
                                type="checkbox"
                            />
                        ) : null}
                        <h4
                            className={
                                "task-title " + setTaskColor(task.taskType)
                            }>
                            {task.taskName}
                        </h4>
                    </div>
                    <div className="card">
                        <div className="card-body">
                            {!task.completed ? (
                                <button
                                    onClick={() =>
                                        alterTask(task.id, deleteData)
                                    }
                                    className="delete-button">
                                    Delete
                                </button>
                            ) : null}
                            <p className="card-text description">
                                {task.description}
                            </p>
                            {checkDeadline() ? (<p className="expiring deadline">
                                Deadline: {task.deadLine}
                            </p>) : (<p className="deadline">
                                Deadline: {task.deadLine}
                            </p>)

                            }
                            <p className="estimated">
                                Estimated: {task.estimatedTime}
                            </p>
                            <img src={setImg()}/>

                        </div>
                    </div>
                </div>
            ) : null}
        </>
    );
};

export default TodoElement;
