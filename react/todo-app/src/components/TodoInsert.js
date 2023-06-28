import React from 'react';
import { MdAdd } from 'react-icons/md';
import './TodoInsert.scss'

const TodoInsert = () => {
  return (
    <form className="TodoInsert">
      <input placeholder="enter your plan"></input>
      <button type="submit">
        <MdAdd></MdAdd>
      </button>
    </form>
  );
};

export default TodoInsert;