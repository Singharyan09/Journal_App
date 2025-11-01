<script setup>
import { onMounted, ref } from 'vue';

const journals = ref([]);

onMounted(() => {
  fetch('http://localhost:8083/Journal')
      .then((response) => response.json())
      .then((data) => {
        journals.value = data;
      })
      .catch((error) => console.error('Error fetching journals:', error));
});
</script>

<template>
  <div style="padding: 20px; font-family: Arial">
    <h1>📓 Journal Entries</h1>
    <ul v-if="journals.length">
      <li
          v-for="journal in journals"
          :key="journal.id"
          style="margin-bottom: 15px; list-style: none; border-bottom: 1px solid #ccc; padding-bottom: 10px;"
      >
        <h2 style="margin: 0;">{{ journal.title }}</h2>
        <p style="color: #555;">{{ journal.content }}</p>
      </li>
    </ul>
    <p v-else>No journal entries found.</p>
  </div>
</template>
