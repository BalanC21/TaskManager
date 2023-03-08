import React from "react";
import { NavLink } from "react-router-dom";
import { useAtom } from "jotai";
import { ORDER_CRITERIA } from "../stateManagement/Store";

export default function NavBar() {
    const [orderCriteria, setOrderCriteria] = useAtom(ORDER_CRITERIA);

    const handleSort = () => {
        if (orderCriteria === "Ascending") {
            setOrderCriteria("Descending");
        } else if (orderCriteria === "Descending") {
            setOrderCriteria("Ascending");
        }
    };

    return (
        <nav>
            <NavLink to="/" className="nav-link">
                Pending
            </NavLink>
            <NavLink to="/completed" className="nav-link">
                Completed
            </NavLink>
            <button onClick={handleSort} className="nav-link sort-by-date">
                Sort By Date {orderCriteria}
            </button>
        </nav>
    );
}
