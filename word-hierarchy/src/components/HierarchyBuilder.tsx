"use client";

import React, { useState } from 'react';
import Node from './Node';
import SaveButton from './SaveButton';
import styles from '../styles/components/HierarchyBuilder.module.scss';
import Image from 'next/image';
import hierarchy from '@/public/hierarchy.svg';

const HierarchyBuilder: React.FC = () => {
  const [tree, setTree] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<number[]>([]); 
  const [nodeItems, setNodeItems] = useState<string>(''); 

  const addNode = (indexPath: number[] | null = null) => {
    const newNode = { name: '', children: [], items: [] };
    const updatedTree = [...tree];

    if (indexPath === null) {
      setTree([...tree, newNode]); 
    } else {
      const addNodeRecursively = (nodes: any[], path: number[], depth: number) => {
        const currentIndex = path[depth];
        if (depth === path.length - 1) {
          nodes[currentIndex].children.push(newNode);
        } else {
          addNodeRecursively(nodes[currentIndex].children, path, depth + 1);
        }
      };

      addNodeRecursively(updatedTree, indexPath, 0);
      setTree(updatedTree);
    }
  };

  const updateNodeName = (indexPath: number[], newName: string) => {
    const updatedTree = [...tree];

    const updateNameRecursively = (nodes: any[], path: number[], depth: number) => {
      const currentIndex = path[depth];
      if (depth === path.length - 1) {
        nodes[currentIndex].name = newName;
      } else {
        updateNameRecursively(nodes[currentIndex].children, path, depth + 1);
      }
    };

    updateNameRecursively(updatedTree, indexPath, 0);
    setTree(updatedTree); 
  };

  const addItemsToNode = (indexPath: number[], items: string) => {
    const updatedTree = [...tree];
    const itemsArray = items.split(',').map((item) => item.trim());

    const updateItemsRecursively = (nodes: any[], path: number[], depth: number) => {
      const currentIndex = path[depth];
      if (depth === path.length - 1) {
        nodes[currentIndex].items = itemsArray; 
      } else {
        updateItemsRecursively(nodes[currentIndex].children, path, depth + 1);
      }
    };

    updateItemsRecursively(updatedTree, indexPath, 0);
    setTree(updatedTree);
  };

  const goToParentCategory = () => {
    if (currentPath.length > 0) {
      const newPath = currentPath.slice(0, currentPath.length - 1);
      const updatedTree = [...tree];

      const removeLastSubcategoryRecursively = (nodes: any[], path: number[], depth: number) => {
        const currentIndex = path[depth];
        if (depth === path.length - 1 && nodes[currentIndex].children.length > 0) {
          nodes[currentIndex].children.pop();
        } else if (depth < path.length - 1) {
          removeLastSubcategoryRecursively(nodes[currentIndex].children, path, depth + 1);
        }
      };

      removeLastSubcategoryRecursively(updatedTree, currentPath, 0);
      setCurrentPath(newPath);
    }
  };

  const generateFinalJSON = () => {
    const treeToJson = (nodes: any[]): any => {
      return nodes.reduce((acc, node) => {
        if (node.children.length > 0) {
          acc[node.name] = treeToJson(node.children);
        } else {
          acc[node.name] = node.items; 
        }
        return acc;
      }, {});
    };

    return JSON.stringify(treeToJson(tree), null, 2);
  };

  return (
    <div className={styles.container}>
      <h1>Construtor de Hierarquia de Palavras</h1>
      <Image src={hierarchy} alt="Logo Hierarquia" />
      {tree.map((node, index) => (
        <Node 
          key={index} 
          node={node} 
          indexPath={[index]} 
          currentPath={currentPath} 
          onAddChild={(path) => {
            setCurrentPath(path);
            addNode(path);
          }} 
          onUpdateName={(path, newName) => updateNodeName(path, newName)} 
          onAddItems={(path, items) => addItemsToNode(path, items)}
        />
      ))}
      <button className={styles.buttonClass} onClick={() => addNode(null)}>Adicionar Nível Superior</button>
      <button className={styles.buttonClass} onClick={goToParentCategory}>Retornar para Categoria Superior</button>
      <div>
        <h3>Resultado JSON:</h3>
        <pre>{generateFinalJSON()}</pre>
      </div>
      <SaveButton tree={tree} />
    </div>
  );
};

export default HierarchyBuilder;
